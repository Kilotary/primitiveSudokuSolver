package sudodu.UI;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import sudodu.UI.Cell;

public class NumberSelector extends GridPane {
    private int number;

    public NumberSelector () {
        number = 0;
        this.setPrefSize(61, 70);
        Cell cell;
        for (int i = 0; i < 9; i++ ) {
            cell = new Cell(i + 1, 20, 20);
            cell.setBorder(new Border(new BorderStroke(Color.DARKGREY,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            cell.setFocusTraversable(false);
            add(cell, i % 3, i / 3);
        }
        cell = new Cell(0, 20, 20);
        cell.setBorder(new Border(new BorderStroke(Color.DARKGREY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        cell.setFocusTraversable(false);
        add(cell, 1, 4);
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public <T extends Event> void addEventHandlerCells(EventType<T> eventType,  EventHandler<? super T> eventHandler) {
        for (Node cell : this.getChildren()) {
            cell.addEventHandler(eventType, eventHandler);
        }
    }
}
