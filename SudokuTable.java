package sudodu.UI;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SudokuTable extends Group {
    private GridPane table;
    private Cell lastClickedBoardCell;
    private boolean isSelect = false;
    private NumberSelector numberSelector = new NumberSelector();

    public SudokuTable() {
        table = new GridPane();
//        table.setGridLinesVisible(true);
        numberSelector.setVisible(false);

        numberSelector.addEventHandlerCells(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            lastClickedBoardCell.setNumber(((Cell) mouseEvent.getSource()).getNumber());
            numberSelector.setVisible(false);
            isSelect = false;
        });

        Cell cell;

        for(int i = 0; i < 81; i++) {
            cell = new Cell(0, 30, 30);
            cell.setFocusTraversable(false);
            cell.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            cell.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                if (!numberSelector.isVisible()) {
                    numberSelector.setVisible(true);
                }
                numberSelector.setLayoutX(mouseEvent.getSceneX() - this.getLayoutX() - 30);
                numberSelector.setLayoutY(mouseEvent.getSceneY() - this.getLayoutY() - 30);
                lastClickedBoardCell = (Cell) mouseEvent.getSource();
                isSelect = true;
            });

            table.add(cell, i % 9, i / 9);
        }
        this.getChildren().addAll(table, numberSelector);
    }

    public void setLastSelectedCell(int number) {
        if (!isSelect) {
            return;
        }
        lastClickedBoardCell.setNumber(number);
        numberSelector.setVisible(false);
        isSelect = false;
    }

    public int[][] getTable() {
        int[][] temp = new int[9][9];
        ObservableList<Node> cellList = table.getChildren();
        for(int i = 0; i < cellList.size(); i++) {
            temp[i / 9][i % 9] = ((Cell) cellList.get(i)).getNumber();
        }
        return temp;
    }

    public void setTable(int[][] m) {
        ObservableList<Node> cellList = table.getChildren();
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                ((Cell)cellList.get(i * 9 + j)).setNumber(m[i][j]);
            }
        }
    }
}
