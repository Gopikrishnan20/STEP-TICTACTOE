public class TicTacToe {

    static char[][] board = new char[3][3];

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

    public static void main(String[] args) {
        initBoard();
        printBoard();
    }
}
