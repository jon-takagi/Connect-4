package ConnectFourPackage;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Connect4Moderator {

    private Color[][] board;
    private int[] lastPlay = new int[2];
    private int turnNo;
    private int humanPlayers;
    ArrayList<Group> groups = new ArrayList<Group>(69);
    Group winningGroup;


    Connect4Player player1;
    Connect4Player player2;

    /**
     * @param board the initial board used to play the game. Should be filled with White tiles, does not have to be. Values must not be null
     * @param hP    the number of human players in the game. In 1 player mode, the human goes first
     */
    public Connect4Moderator(Color[][] board, int hP) {
//        try {
//            System.setErr(new PrintStream(new FileOutputStream("errorFile.txt")));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


        this.board = board;
        turnNo = 0;
        this.humanPlayers = hP;
        switch (humanPlayers) {
            case 0:
                player1 = new RandomAI();
                player2 = new SmartAi();
                break;
            case 1:
                player1 = new Connect4UI();
                player2 = new SmartAi();
                break;
            case 2:
                player1 = new Connect4UI();
                player2 = new Connect4UI();
                break;
        }
        getGroups();

    }

    private void getGroups() {

//                    Initialize and place the groups
        int[] dRows = {-1, 0, 1, 1};
        int[] dCols = {1, 1, 1, 0};
        int fRow, fCol;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

//                start @ i,j
//
//                if 0 < i + 3 * dCol <= board[0].length, 0 < j + 3 * dRow <= board.length
//                    create new group from i,j with specified increment pair
//
//                possible directions are: diagonal up, right, diagonal down, down
//                / -1 row +1 col
//                > 0 row +1 col
//                \ +1 row +1 col
//                | +1 row 0 col


                for (int k = 0; k < dRows.length; k++) {
                    fRow = i + 3 * dRows[k];
                    fCol = j + 3 * dCols[k];

//                    switch (k) {
//                        case 0:
//                            System.out.print("/ ");
//                            break;
//                        case 1:
//                            System.out.print("> ");
//                            break;
//                        case 2:
//                            System.out.print("\\ ");
//                            break;
//                        case 3:
//                            System.out.print("| ");
//                            break;
//                    }
                    if (fRow >= 0 && fRow <= board.length - 1 && fCol >= 0 && fCol <= board[0].length - 1) {
//                        System.out.println("Group: [" + j + "][" + i + "] to [" + fRow + "][" + fCol + "] is possible");
                        groups.add(new Group(board, i, j, dCols[k], dRows[k]));

                    }
                }


            }
        }
    }

    /**
     * @return the Color of the token being used by the winner. Returns Color.White if no winner yet exists
     */
    public Color getWinner() {
        for (Group group : groups) {
//            System.out.println("groups " + i + " isFull = " + groups.get(i).isFull());
            if (group.isFull() && !group.getFullColor().equals(Color.WHITE)) {
                winningGroup = group;
                return group.getFullColor();
            }
        }
        return Color.WHITE;
    }

    public void printBoard() {
        System.out.println("turnNo = " + turnNo);
//        System.out.println("  a b c d e f g");
        for (int i = 0; i < board.length; i++) {

//            System.out.print((6 - i) + " ");
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].equals(Color.WHITE))
                    System.out.print(". ");
                if (board[i][j].equals(Color.RED)) {
                    if (i == lastPlay[0] && j == lastPlay[1])
                        System.out.print("R ");
                    else
                        System.out.print("r ");
                }
                if (board[i][j].equals(Color.YELLOW)) {
                    if (i == lastPlay[0] && j == lastPlay[1]) {

                        System.out.print("Y ");
                    } else {
                        System.out.print("y ");
                    }
                }
            }
            System.out.print(" " + i);
            System.out.print("\n");
        }
        System.out.println("0 1 2 3 4 5 6");
//        System.out.println("  0 1 2 3 4 5 6");
    }

    public Color[][] getBoard() {
        return board;
    }

    public void dropToken(int col) {
        turnNo++;
        for (int i = 5; i >= 0; i--) {
            if (board[i][col].equals(Color.WHITE)) {
                lastPlay[0] = i;
                lastPlay[1] = col;
//                board[i][col] = Color.YELLOW;
                if (turnNo % 2 == 0) {
                    board[i][col] = Color.YELLOW;
                    break;
                } else {
                    board[i][col] = Color.RED;
                    break;
                }
            }

        }
    }

    public Color playGame() {
//        System.out.println(player1.getClass() + "  is " + Color.RED +" / RED (R)");
//        System.out.println(player2.getClass() + " is "  + Color.YELLOW + " / YELLOW (Y)");
        while (getWinner().equals(Color.WHITE)) {
//            System.out.print("\nRED to move, ");
            dropToken(player1.getMove(board, Color.RED));
//            printBoard();
            if (humanPlayers == 2)
                printBoard();
            if (!getWinner().equals(Color.WHITE)) {
                return getWinner();
            }
//            System.out.print("\nYELLOW to move, ");
            dropToken(player2.getMove(board, Color.YELLOW));
//            printBoard();
            if (turnNo > 42)
                return Color.WHITE;
        }
//        printBoard();
        return getWinner();
    }
}
