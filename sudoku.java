import java.util.Scanner;

class sudoku {
    public static final Scanner sc = new Scanner(System.in);
    public static int board[][] = new int[9][9];

    // TAKES THE INPUT OF QUESTION OF SUDOKU
    public static void inputQ() {
        System.out.println("\t\t\tENTER THE QUESTION\n");
        for (int i = 0; i < 9; i++) {
            System.out.println("ENTER ROW " + (i + 1) + " : ");
            for (int j = 0; j < 9; j++) {
                board[i][j] = sc.nextInt();
                if (board[i][j] > 10 || board[i][j] < 0) {
                    System.out.println("INVALID INPUT !!");
                    System.exit(0);
                }
            }
        }
    }

    // PRINTS THE INPUT QUESTION OF SUDOKU
    public static void outputQ() {
        System.out.print("\033[H\033[2J");
        System.out.println("\t\t\tYOUR QUESTION IS\n");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
                if (j == 2 || j == 5)
                    System.out.print(" | ");
            }
            System.out.println();
            if (i == 2 || i == 5)
                System.out.println("-------+--------+-------");
        }

    }

    // CHECKS WHETHER THE NUMBER IS PRESENT IN ROW OR NOT
    private static boolean isNumInRow(int num, int row) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num)
                return true;
        }
        return false;
    }

    // CHECKS WHETHER THE NUMBER IS PRESENT IN COLUMN OR NOT
    private static boolean isNumInColumn(int num, int col) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num)
                return true;
        }
        return false;
    }

    // CHECKS WHETHER THE NUMBER IS PRESENT IN BOX OR NOT
    private static boolean isNumInBox(int num, int row, int col) {
        int boxTopRow = row - row % 3;
        int boxTopCol = col - col % 3;

        for (int i = boxTopRow; i < boxTopRow + 3; i++) {
            for (int j = boxTopCol; j < boxTopCol + 3; j++) {
                if (board[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    // CHECKS WHETHER NUMBER WE TRY IS VALID OR NOT
    private static boolean isValid(int num, int row, int col) {
        return !isNumInRow(num, row) && !isNumInColumn(num, col) && !isNumInBox(num, row, col);
    }

    // RECURSION TO FIND SOLUTION FOR EVERY 0 USING RECURSION AND BACKTRACKING
    private static boolean solveBoard() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int numToTry = 1; numToTry <= 9; numToTry++) {
                        if (isValid(numToTry, row, col)) {
                            board[row][col] = numToTry;
                            if (solveBoard())
                                return true;
                            else
                                board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // PRINTS THE SOLUTION OF THE GIVEN SUDOKU
    private static void printResult() {
        System.out.println("\t\t\tYOUR SOLUTION IS\n");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
                if (j == 2 || j == 5)
                    System.out.print(" | ");
            }
            System.out.println();
            if (i == 2 || i == 5)
                System.out.println("-------+--------+-------");
        }
    }

    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.println("\t\tWELCOME TO 9x9 SUDOKU SOLVER IN JAVA\n");
        System.out.println("- INPUT 0 IN PLACE OF BLANKS IN QUESTION");
        System.out.println("- THIS PROJECT CAN EASILY VALIDATE AND SOLVE SUDOKU PUZZLES\n\n");
        inputQ();
        long start = System.currentTimeMillis();
        outputQ();
        if (solveBoard()) {
            System.out.println("\n\t\tSUDOKU SOLVED SUCCESSFULLY !!\n");
            printResult();
            System.out.println("\nIT TOOK " + (System.currentTimeMillis() - start) + "ms TO SOLVE THIS PUZZLE");
        } else
            System.out.println("\n\t\t\tSUDOKU IS UNSOLVABLE !!\n");
    }
}