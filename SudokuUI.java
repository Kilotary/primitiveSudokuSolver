package sudodu.UI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SudokuUI extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        int[][] m2 = new int[][]{
                {4, 7, 9, 6, 0, 0, 1, 3, 0},
                {5, 0, 8, 0, 1, 9, 7, 6, 0},
                {1, 0, 0, 7, 3, 0, 5, 0, 8},
                {0, 0, 3, 5, 6, 0, 4, 0, 7},
                {2, 5, 0, 0, 9, 0, 0, 0, 3},
                {0, 0, 0, 0, 4, 2, 9, 1, 5},
                {0, 2, 6, 0, 0, 1, 0, 0, 9},
                {0, 0, 0, 0, 0, 6, 0, 0, 0},
                {0, 0, 0, 4, 0, 0, 0, 0, 6},
        };
        Sudoku2 sudoku = new Sudoku2();
        int tableX = 100;
        int tableY = 50;
        Group group = new Group();
        SudokuTable table = new SudokuTable();

        table.setLayoutX(tableX);
        table.setLayoutY(tableY);

        sudoku.setBoard(m2);
        table.setTable(m2);

        group.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            int keyCode = keyEvent.getCode().getCode();
            System.out.println(keyCode);
            if (keyCode < 48 || keyCode > 57) {
                return;
            }
            table.setLastSelectedCell(keyCode - 48);
        });



        Button solveButton = new Button("Solve");
        solveButton.setLayoutX(tableX + 80);
        solveButton.setLayoutY(tableY + 300);
        solveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            sudoku.setBoard(table.getTable());
            sudoku.solve();
            table.setTable(sudoku.getBoard());
        });

        Button resetButton = new Button("Reset");
        resetButton.setLayoutX(tableX + 160);
        resetButton.setLayoutY(tableY + 300);
        resetButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            sudoku.setBoard(new int[9][9]);
            table.setTable(sudoku.getBoard());
        });

        group.getChildren().addAll(solveButton, resetButton, table);

        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.show();
    }
}
