package edu.hw1;

public class Task8 {

    private static final int SIZE = 8;
    private static final int[] DX = {-2, -2, -1, -1, 1, 1, 2, 2};
    private static final int[] DY = {-1, 1, -2, 2, -2, 2, -1, 1};

    private Task8() {

    }

    public static boolean knightBoardCapture(int[][] board) {
        if (board == null) {
            return false;
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 1 && canCapture(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean canCapture(int[][] board, int x, int y) {
        for (int k = 0; k < SIZE; k++) {
            int newX = x + DX[k];
            int newY = y + DY[k];
            if (isValidMove(newX, newY) && board[newX][newY] == 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidMove(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

}
