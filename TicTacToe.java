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

    // Places the symbol on the board at the given position (state update)
    static void placeSymbol(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // Returns true if the given symbol owns an entire row, column, or diagonal
    static boolean checkWin(char symbol) {
        // rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return true;
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) return true;
        }
        // diagonals
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true;

        return false;
    }

    // Keeps picking a random slot (1-9) until it lands on an empty cell, then places the symbol
    static void computerMove() {
        Random random = new Random();
        int row, col;

        do {
            int slot  = random.nextInt(9) + 1;   // 1–9
            int[] idx = slotToIndices(slot);
            row = idx[0];
            col = idx[1];
        } while (!isValidMove(row, col));

        char symbol = (currentPlayer == 1) ? player1Symbol : player2Symbol;
        placeSymbol(row, col, symbol);
        System.out.println("Computer (Player " + currentPlayer + ") placed " + symbol +
                " at row " + row + ", col " + col + ":");
        printBoard();
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
            char symbol = (currentPlayer == 1) ? player1Symbol : player2Symbol;
            placeSymbol(row, col, symbol);
            System.out.println("Board after Player " + currentPlayer + " placed " + symbol + " at slot " + slot + ":");
            printBoard();
            if (checkWin(symbol)) {
                System.out.println("Player " + currentPlayer + " wins!");
                return;
            }
        } else {
            System.out.println("Slot " + slot + " rejected: out of bounds or cell already taken.");
            return;
        }

        // Switch to the other player and let the computer take a turn
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
        System.out.println();
        computerMove();
        char compSymbol = (currentPlayer == 1) ? player1Symbol : player2Symbol;
        if (checkWin(compSymbol)) {
            System.out.println("Player " + currentPlayer + " (computer) wins!");
        }
    }
}
