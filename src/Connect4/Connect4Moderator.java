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
                player1 = new RandomAI(this, Color.RED);
                player2 = new RandomAI(this, Color.YELLOW);
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
                /*
                start @ i,j

                if 0 < i + 3 * dCol <= board[0].length, 0 < j + 3 * dRow <= board.length
                    create new group from i,j with specified increment pair

                possible directions are: diagonal up, right, diagonal down, down
                / -1 row +1 col
                > 0 row +1 col
                \ +1 row +1 col
                | +1 row 0 col
                    */

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
                    if (fRow >= 0 && fRow < board.length && fCol >= 0 && fCol < board[0].length) {
//                        System.out.println("Group: [" + j + "][" + i + "] to [" + fRow + "][" + fCol + "] is possible");
                        groups.add(new Group(this, i, j, dCols[k], dRows[k]));
                    }
                }


            }
        }

    }

    public Color getWinner() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (checkAdjacent(i, j, 0, Color.RED) == 1) {
                    return Color.RED;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (checkAdjacent(i, j, 0, Color.YELLOW) == 1) {
                    return Color.YELLOW;
                }
            }
        }
        return Color.WHITE;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public Color currentPlayer() {
        if (turnNo % 2 == 0)
            return Color.YELLOW;
        return Color.RED;
    }

    public boolean hasWinner() {
        return !getWinner().equals(Color.WHITE);
    }

    public void clear() {

    }

    private int checkAdjacent(int r, int c, int count, Color winner) {
        if (count >= 3) {
            if (board[r][c].equals(winner))
                return 1;
        }
        if (r + 1 < board.length) {
            if (board[r][c].equals(board[r + 1][c])) {
                return checkDown(r + 1, c, count + 1, winner);
            }
        }
        if (r + 1 < board.length && c + 1 < board[r].length) {
            if (board[r][c].equals(board[r + 1][c + 1])) {
                return checkDiag(r + 1, c + 1, count + 1, winner);
            }
        }
        if (c + 1 < board[r].length) {
            if (board[r][c].equals(board[r][c + 1])) {
                return checkRight(r, c + 1, count + 1, winner);
            }
        }

        return -1;
    }

    private int checkDown(int r, int c, int count, Color winner) {
        if (count >= 3) {
            if (board[r][c].equals(winner)) {
                return 1;
            }
        }
        if (r + 1 < board.length) {
            if (board[r][c].equals(board[r + 1][c])) {
                return checkDown(r + 1, c, count + 1, winner);
            }
        }
        return -1;

    }

    private int checkRight(int r, int c, int count, Color winner) {
        if (count >= 3) {
            if (board[r][c].equals(winner))
                return 1;
        }
        if (c + 1 < board[r].length) {
            if (board[r][c].equals(board[r][c + 1])) {
                return checkRight(r, c + 1, count + 1, winner);
            }
        }
        return -1;
    }

    private int checkDiag(int r, int c, int count, Color winner) {
        if (count >= 3) {
            if (board[r][c].equals(winner))
                return 1;
        }
        if (r + 1 < board.length && c + 1 < board[r].length) {
            if (board[r][c].equals(board[r + 1][c + 1])) {
                return checkDiag(r + 1, c + 1, count + 1, winner);
            }
        }
        return -1;
    }

    public void printBoard() {
        System.out.println("  a b c d e f g");
        for (int i = 0; i < board.length; i++) {

            System.out.print((6 - i) + " ");
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].equals(Color.WHITE))
                    System.out.print(". ");
                if (board[i][j].equals(Color.RED))
                    System.out.print("X ");
                if (board[i][j].equals(Color.YELLOW))
                    System.out.print("O ");
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
                if (turnNo % 2 == 0) {
                    board[i][col] = Color.YELLOW;
                } else {
                    board[i][col] = Color.RED;
                }
                return;
            }
        }
    }

    public Color playGame() {
//        System.out.println(!hasWinner());
        printBoard();
        while (!hasWinner()) {
            dropToken(player1.makePlay());
//            if (humanPlayers == 2)
                printBoard();
            if (!getWinner().equals(Color.WHITE)) {
                System.out.println("The winner is player 1");
                break;
            }
            dropToken(player2.makePlay());
        }
        return getWinner();
    }

}
