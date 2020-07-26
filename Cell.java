package sudodu.UI;

import javafx.scene.control.Button;

public class Cell extends Button {
    private int number;

    public Cell(int number, int width, int height) {
        setPrefSize(width, height);
        setText(Integer.toString(number));
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        setText(Integer.toString(number));
        this.number = number;
    }
}