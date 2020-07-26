package sudodu.UI;

public class Board2 {
    private int[][] board;
    private int[][] rows;
    private int[][] cols;
    private int[][] squares;

    public Board2(int[][] board) {
        setBoard(board);
    }

    public void insert(int row, int col, int number) {
        if (board[row][col] == number) return;

        rows[row][board[row][col]]--;
        cols[col][board[row][col]]--;
        squares[toSquare(row, col)][board[row][col]]--;

        board[row][col] = number;

        rows[row][number]++;
        cols[col][number]++;
        squares[toSquare(row, col)][number]++;
    }

    public boolean canInsert(int row, int col, int number) {
        if (!isEmpty(row, col)) return false;
        if (containsInColumn(col, number)) return false;
        if (containsInRow(row, number)) return false;
        if (containsInSquare(row, col, number)) return false;
        return true;
    }

    public boolean isEmpty(int row, int col) {
        return board[row][col] == 0;
    }

    public boolean isCorrect() {
        for(int i = 0; i < 9; i++) {
            for(int j = 1; j < 10; j++){
                if (rows[i][j] > 1 || cols[i][j] > 1 || squares[i][j] > 1) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean containsInRow(int row, int number) {
        return rows[row][number] > 0;
    }

    public boolean containsInColumn(int col, int number) {
        return cols[col][number] > 0;
    }

    public boolean containsInSquare(int row, int col, int number) {
        return squares[toSquare(row,col)][number] > 0;
    }

    public int toSquare(int row, int col) {
        return col / 3 + row / 3 * 3;
    }

    public void setBoard(int[][] board) {
        int[][] temp = new int[9][9];
        rows = new int[9][10];
        cols = new int[9][10];
        squares = new int[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int number = board[i][j];

                temp[i][j] = number;
                rows[i][number]++;
                cols[j][number]++;
                squares[toSquare(i, j)][number]++;
            }
        }
        this.board = temp;
    }
    public int[][] getBoard() {
        return board;
    }

    public void print() {
        for(int[] i : board) {
            for(int j : i) {
                System.out.print("|" + j + "|");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {

        int[][] m = new int[][]{
                {8, 7, 1, 3, 2, 5, 6, 3, 9},
                {4, 3, 9, 6, 2, 6, 1, 5, 1},
                {2, 5, 6, 0, 9, 1, 0, 7, 4},
                {0, 3, 0, 4, 7, 0, 0, 0, 6},
                {5, 0, 8, 1, 0, 2, 4, 0, 0},
                {7, 0, 0, 0, 3, 8, 9, 0, 2},
                {0, 2, 0, 0, 0, 3, 0, 6, 0},
                {0, 0, 0, 0, 8, 7, 0, 4, 0},
                {0, 8, 3, 2, 5, 0, 7, 0, 0},
        };
        int[][] m1 = new int[][]{
                {8, 0, 1, 0, 0, 5, 6, 0, 9},
                {0, 0, 9, 0, 0, 0, 0, 5, 0},
                {0, 5, 6, 0, 9, 1, 0, 7, 4},
                {0, 3, 0, 4, 7, 0, 0, 0, 6},
                {5, 0, 8, 1, 0, 2, 4, 0, 0},
                {7, 0, 0, 0, 3, 8, 9, 0, 2},
                {0, 2, 0, 0, 0, 3, 0, 6, 0},
                {0, 0, 0, 0, 8, 7, 0, 4, 0},
                {0, 8, 3, 2, 5, 0, 7, 0, 0},
        };
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

//        Board2 b = new Board2(m1);
//        b.insert(0, 1, 2);
//        System.out.println(b.canInsert(0, 3, 3));
//        b.print();
//        b.print();
//        System.out.println(b.canInsert(2, 0, 0));
//        System.out.println(b.isEmpty(0, 0));
//        System.out.println(b.containsInColumn(2, 0));
//        System.out.println(b.containsInRow(2, 0));

        Sudoku2 sudoku = new Sudoku2(m2);
        long t1 = System.currentTimeMillis();
        sudoku.solve();
        long t2 = System.currentTimeMillis();
        sudoku.printBoard();
        System.out.println(t2 - t1);
        System.out.println(sudoku.t);
//        System.out.println(b.numInBoard);
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
////                System.out.println(b.toSquare(i, j) + " || "  + i + " || "+j);
//                for (int k = 1; k < 10; k++) {
//                    System.out.println(b.toSquare(i, j) + " || "  + k + " || "+b.containsInSquare(k, b.toSquare(i, j)));
//                }
//            }
//        }
//        System.out.println(b.isFinish());
    }
}
