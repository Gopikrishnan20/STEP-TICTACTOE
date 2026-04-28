import java.util.Random;

public class TicTacToe {

    static char[][] board = new char[3][3];

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

    public static void main(String[] args) {
        initBoard();
        toss();
        printBoard();
    }
}
