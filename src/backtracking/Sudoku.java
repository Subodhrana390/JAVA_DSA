package backtracking;

import java.util.Vector;

public class Sudoku {

    boolean isSafe(int row, int col, Vector<Vector<Integer>> board, int val) {
        for (int i = 0; i < board.size(); i++) {
            if (board.get(row).get(i) == val) return false;
            if (board.get(i).get(col) == val) return false;

            int subgridRow = 3 * (row / 3) + i / 3;
            int subgridCol = 3 * (col / 3) + i % 3;
            if (board.get(subgridRow).get(subgridCol) == val) return false;
        }
        return true;
    }

    boolean solve(Vector<Vector<Integer>> board) {
        int n = board.size();

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (board.get(row).get(col) == 0) {
                    for (int val = 1; val <= 9; val++) {
                        if (isSafe(row, col, board, val)) {
                            board.get(row).set(col, val);
                            if (solve(board)) {
                                return true;
                            }
                            board.get(row).set(col, 0);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    void solveSudoku(Vector<Vector<Integer>> sudoku) {
        solve(sudoku);
    }

    public static void main(String[] args) {
        Vector<Vector<Integer>> board = new Vector<>();

        // Example input: a 9x9 board with some zeros (unfilled cells)
        int[][] grid = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        for (int[] row : grid) {
            Vector<Integer> vecRow = new Vector<>();
            for (int val : row) {
                vecRow.add(val);
            }
            board.add(vecRow);
        }

        Sudoku solver = new Sudoku();
        solver.solveSudoku(board);

        // Print solved board
        for (Vector<Integer> row : board) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
