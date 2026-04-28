import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static char[][]  board = new char[3][3];
    static Scanner   scanner = new Scanner(System.in);

    // Game state variables
    static int    currentPlayer;   // 1 or 2
    static char   player1Symbol;
    static char   player2Symbol;

    // Fills every cell with '-' to mark it as empty
    static void initBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    static void printBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col]);
                if (col < 2) System.out.print(" ");
            }
            System.out.println();
        }
    }

    // Randomly decides who starts and assigns X / O accordingly
    static void toss() {
        Random random = new Random();
        int result = random.nextInt(2); // 0 or 1

        if (result == 0) {
            currentPlayer = 1;
            player1Symbol = 'X';
            player2Symbol = 'O';
        } else {
            currentPlayer = 2;
            player2Symbol = 'X';
            player1Symbol = 'O';
        }

        System.out.println("Toss result: Player " + currentPlayer + " goes first!");
        System.out.println("Player 1 = " + player1Symbol + " | Player 2 = " + player2Symbol);
        System.out.println();
    }

    // Converts slot (1-9) to a row/col pair using zero-based indexing
    // index = slot - 1  →  row = index / 3  ,  col = index % 3
    static int[] slotToIndices(int slot) {
        int index = slot - 1;
        int row   = index / 3;
        int col   = index % 3;
        return new int[]{row, col};
    }

    // Returns true only when row/col are within bounds AND the cell is not yet taken
    static boolean isValidMove(int row, int col) {
        boolean inBounds  = (row >= 0 && row <= 2) && (col >= 0 && col <= 2);
        boolean cellEmpty = inBounds && board[row][col] == '-';
        return inBounds && cellEmpty;
    }

    // Reads and returns a valid slot number (1–9) from the current player
    static int getPlayerInput() {
        System.out.print("Player " + currentPlayer + " (" +
                (currentPlayer == 1 ? player1Symbol : player2Symbol) +
                ") - enter slot (1-9): ");
        int slot = scanner.nextInt();
        return slot;
    }

    public static void main(String[] args) {
        initBoard();
        toss();
        printBoard();

        int slot      = getPlayerInput();
        int[] indices = slotToIndices(slot);
        int   row     = indices[0];
        int   col     = indices[1];

        if (isValidMove(row, col)) {
            System.out.println("Slot " + slot + " (row " + row + ", col " + col + ") - move accepted.");
        } else {
            System.out.println("Slot " + slot + " (row " + row + ", col " + col + ") - move rejected: out of bounds or cell already taken.");
        }
    }
}
