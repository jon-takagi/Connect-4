package Connect4;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Connect4Moderator implements Connect4able {

    private Color[][] board;
    private int turnNo;
    private int humanPlayers;
    ArrayList<Group> groups = new ArrayList<Group>(69);

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
                player1 = new SmartAi(this, Color.RED);
                player2 = new SmartAi(this, Color.YELLOW);
                break;
            case 1:
                player1 = new Connect4UI(this, Color.RED);
                player2 = new SmartAi(this, Color.YELLOW);
                break;
            case 2:
                player1 = new Connect4UI(this, Color.RED);
                player2 = new Connect4UI(this, Color.YELLOW);
                break;
        }
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
                        groups.add(new Group(this, i, j, dCols[k], dRows[k]));
                    }
                }


            }
        }

    }

    /**
     * @return the Color of the token being used by the winner. Returns Color.White if no winner yet exists
     */
    public Color getWinner() {
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).isFull() && !groups.get(i).getFullColor().equals(Color.WHITE))
                return groups.get(i).getFullColor();
        }
        return Color.WHITE;
    }

    @Override
    /**
     * returns whether the game is over or not
     */
    public boolean isGameOver() {
        return turnNo > 42;
    }

    /**
     * @return the Color of the player currently playing
     */
    @Override
    public Color currentPlayer() {
        if (turnNo % 2 == 0)
            return Color.YELLOW;
        return Color.RED;
    }

    /**
     * @return whether the winner of the game is one of the players, rather than white
     */
    public boolean hasWinner() {
        return !getWinner().equals(Color.WHITE);
    }

    /**
     * does nothing
     */
    public void clear() {

    }


    public void printBoard() {
        System.out.println("  a b c d e f g");
        for (int i = 0; i < board.length; i++) {

            System.out.print((6 - i) + " ");
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].equals(Color.WHITE))
                    System.out.print(". ");
                if (board[i][j].equals(Color.RED))
                    System.out.print("R ");
                if (board[i][j].equals(Color.YELLOW))
                    System.out.print("Y ");
            }
            System.out.print(" " + i);
            System.out.print("\n");
        }
//        System.out.println("0 1 2 3 4 5 6");
        System.out.println("  0 1 2 3 4 5 6");
    }

    public Color[][] getBoard() {
        return board;
    }

    public void dropToken(int col) {
        turnNo++;
        for (int i = 5; i >= 0; i--) {
            if (board[i][col].equals(Color.WHITE)) {
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
        while (getWinner().equals(Color.WHITE)) {
//            System.out.println(getWinner());
            dropToken(player1.getMove(board));
            if (humanPlayers == 2)
                printBoard();
            if (!getWinner().equals(Color.WHITE)) {
                return getWinner();
            }
            dropToken(player2.getMove(board));
        }
        return getWinner();
    }

}
