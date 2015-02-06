package ConnectFourPackage;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by 40095 on 1/24/15.
 */
public class SmartAi extends Connect4Player implements Connect4AI {
    ArrayList<Group> winningGroups, losingGroups, zugzwangGroups, winnableGroups, groups;
    //Winning groups is all groups that can be won on the next play
    //Losing groups is all the groups that will lead to defeat in the next play
    //future threats are all groups with three of a kind, but the 4th tile is not playable (Zugzwang)
    //winnableGroups is all groups that do not contain the opponents tile
    //groups is all groups


    /*
    Order of precedence for plays to make:
    1) plays that make you win immediately
        Pick first group from left
    2) plays that keep you from losing immediately
        Pick first group from left
    3) plays that are in a winnable group AND not zugzwang
        Pick winnable group with most of your own tokens
    4) zugzwang plays
        Pick first group from left

     */

    public SmartAi() {
//        getGroups();
        groups = new ArrayList<>(69);
        winningGroups = new ArrayList<Group>();
        losingGroups = new ArrayList<Group>();
        zugzwangGroups = new ArrayList<Group>();
        winnableGroups = new ArrayList<>();
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

    private boolean isPlayable(int row, int col) {
        if (row == 5)
            return board[row][col].equals(Color.WHITE);
        return board[row][col].equals(Color.WHITE) && !board[row + 1][col].equals(Color.WHITE);
    }

    private int tilesBelowSpace(int row, int col) {
        int count = 0;
        for (int i = row; i < 5; i++) {
            if (board[i][col].equals(Color.WHITE)) {
                count++;
            }
        }
        return count;
    }

    private int winningCol() {
        refreshGroups();

        if (winnableGroups.size() > 0) {
            return winningGroups.get(0).dangerousCol();
        }
        if (losingGroups.size() > 0) {
            return losingGroups.get(0).dangerousCol();
        }

        return 1;
    }

    private void setWinningGroups() {
        winnableGroups.clear();
        for (Group group : groups) {
            if (group.dangerousCol() > 0 && group.dangerousRow() > 0) {
                if (group.getDangerous() != -1 && group.getCriticalColor().equals(token) && isPlayable(group.dangerousCol(), group.dangerousRow())) {
                    winningGroups.add(group);
                }
            }
        }

    }

    private void setLosingGroups() {
        losingGroups.clear();
        for (Group group : groups) {
            if (group.dangerousCol() > 0 && group.dangerousRow() > 0) {
                if (group.getDangerous() != -1 && !group.getCriticalColor().equals(token) && isPlayable(group.dangerousCol(), group.dangerousRow())) {
                    losingGroups.add(group);
                }
            }
        }

    }

    private void setWinnableGroups() {
        for (Group g : groups) {
            if (token.equals(Color.RED)) {
                if (g.doesNotContain(Color.YELLOW)) {
                    winnableGroups.add(g);
                }
            } else {
                if (g.doesNotContain(Color.RED)) {
                    winnableGroups.add(g);
                }
            }
        }
    }

    private void setZugzwangGroups() {
        for (Group g : groups) {
            if (!isPlayable(g.dangerousRow(), g.dangerousCol())) //if the group is not playable
                zugzwangGroups.add(g);
        }
    }

    private void refreshGroups() {
        setWinningGroups();
        setLosingGroups();
        setWinnableGroups();
        setZugzwangGroups();
    }

    public int makePlay() {
        if (winningCol() != -1) {
//            System.out.println("Playing critical column");
//            if (winningGroups.size() == 0)
//                System.out.println("Not on my watch");
//            else
//                System.out.println("I win!");
            return winningCol();
        } else {
//            System.out.println("playing random");
        }
        return -1;
    }

    @Override
    public int getMove(Color[][] board, Color me) {
        this.token = me;
        this.board = board;
        getGroups();
        return makePlay();
    }
}