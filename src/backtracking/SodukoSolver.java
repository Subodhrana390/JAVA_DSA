package backtracking;

public class SodukoSolver {

    public void solveSudoku(char[][] board) {
        helper(board, 0, 0);
    }

    public boolean helper(char[][] board, int row, int col) {
        if (row == 9) {
            return true;
        }

        int nrow = 0;
        int ncol = 0;

        if (col != 8) {
            nrow = row;
            ncol = col + 1;
        } else {
            nrow = row + 1;
            ncol = 0;
        }

        if (board[row][col] != '.') {
            return helper(board, nrow, ncol);
        }

        for (int i = 1; i <= 9; i++) {
            if (isSafe(board, row, col, i)) {
                board[row][col] = (char) (i + '0');
                if (helper(board, nrow, ncol)) {
                    return true;
                }
                board[row][col] = '.';
            }
        }

        return false;
    }

    public boolean isSafe(char[][] board, int row, int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == (char) (number + '0') || board[row][i] == (char) (number + '0')) {
                return false;
            }
        }

        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (board[i][j] == (char) (number + '0')) {
                    return false;
                }
            }
        }
        return true;
    }
}
