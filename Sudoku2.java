package sudodu.UI;

import java.util.ArrayList;
import java.util.List;

public class Sudoku2 {
    private Board2 board;
    private List<int[]> nullCells;
    int t = 0;

    public Sudoku2() {
        this.board = new Board2(new int[9][9]);
    }

    public Sudoku2(int[][] board) {
        this.board = new Board2(board);
        }

    public void setBoard(int[][] m) {
        board.setBoard(m);
    }

    public int[][] getBoard() {
        return board.getBoard();
    }

    public int solve() {
        nullCells = findNullCells();
        if (!board.isCorrect()) {
            return 0;
        }
        return solve(0);
    }

    public int solve(int n) {
        t++;
        if (nullCells.size() <= n) {
            return 1;
        }
        int[] nullCell = nullCells.get(n);
        int res = 0;

        for (int i = 1; i <= 9; i++) {
            if (!board.canInsert(nullCell[0], nullCell[1], i)) {
                continue;
            }
            board.insert(nullCell[0], nullCell[1], i);
            res = solve(n + 1);
            if (res == 1) {
                return res;
            } else {
                board.insert(nullCell[0], nullCell[1], 0);
            }
        }

        return res;
    }



    private List<int[]> findNullCells() {
        ArrayList<int[]> temp = new ArrayList<>();
        int[][] m = board.getBoard();
        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[i].length; j++) {
                if (m[i][j] == 0) {
                    temp.add(new int[]{i, j});
                }
            }
        }
        return temp;
    }

//    private boolean isNullCellsEmpty() {
//        return nullCells.isEmpty();
//    }

    public void printBoard() {
        board.print();
    }
}
