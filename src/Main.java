import java.util.*;
            /*
            This board can be filled with any sudoku you want, just change the numbers using 0's for blanks spaces
            I generated a random board from www.websudoku.com, enjoy!
            */
public class Main {

    private static final int GRIDSIZE = 9;

    public static void main(String[] args) {

            int[][] board = {
                    {0, 4, 0, 0, 0, 0, 7, 2, 0},
                    {0, 5, 3, 2, 0, 0, 9, 4, 0},
                    {0, 0, 7, 0, 3, 4, 0, 8, 0},
                    {0, 0, 5, 0, 2, 0, 0, 0, 6},
                    {0, 9, 0, 0, 5, 0, 0, 3, 0},
                    {3, 0, 0, 0, 8, 0, 1, 0, 0},
                    {0, 7, 0, 5, 1, 0, 3, 0, 0},
                    {0, 3, 9, 0, 0, 2, 5, 1, 0},
                    {0, 1, 6, 0, 0, 0, 0, 9, 0}
            };

            System.out.println("Unsolved:");
            System.out.println();
            printBoard(board);

            if(solvingAlg(board)) {
                System.out.println("\nSolved:");
                System.out.println();
            } else {
                System.out.println("\nInvalid or Unsolvable Board");
                System.out.println();
            }
            printBoard(board);

    }
    /*
     * Method To print the board inserting a | every 3 numbers, and a bar every 3 columns.
     */
    private static void printBoard(int[][] board) {
        for(int row = 0; row < GRIDSIZE; row++) {
            if(row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for(int col = 0; col < GRIDSIZE; col++) {
                if(col % 3 == 0 && col != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][col]);
            }
            System.out.println();
        }
    }
    /*
     * Method check if a number is in the row by iterating through each row, returns true/false depending on if there
     * is a conflicting number in the row. True = conflicting, False = non-conflicting
     */
    private static boolean isNumInRow(int[][] board, int num, int row) {
        for(int i = 0; i < GRIDSIZE; i++) {
            if(board[row][i] == num) {
                return true;
            }
        }
        return false;
    }
    /*
     *  Does the exact same thing as isNumInRow method but checks for columns instead.
     */
    private static boolean isNumInCol(int[][] board, int num, int col) {
        for(int i = 0; i < GRIDSIZE; i++) {
            if(board[i][col] == num) {
                return true;
            }
        }
        return false;
    }
    /*
     * Checks the 3x3 square for conflicting numbers.
     */
    private static boolean isNumInSquare(int[][] board, int num, int row, int col) {
        int currBoxRow = row - row % 3;
        int currBoxCol = col - col % 3;

        for(int i = currBoxRow; i < currBoxRow + 3; i++) {
            for(int j = currBoxCol; j < currBoxCol + 3; j++) {
                if(board[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValid(int[][] board, int num, int row, int col) {
        return !isNumInRow(board, num, row) && !isNumInCol(board, num, col) && !isNumInSquare(board, num, row, col);
    }

    private static boolean solvingAlg(int[][] board) {
        for(int row = 0; row < GRIDSIZE; row++) {
            for(int col = 0; col < GRIDSIZE; col++) {
                if(board[row][col] == 0) {
                    for(int numTry = 1; numTry <= GRIDSIZE; numTry++) {
                        if(isValid(board, numTry, row, col)) {
                            board[row][col] = numTry;
                            if(solvingAlg(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}