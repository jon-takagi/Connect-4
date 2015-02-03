package Connect4;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by 40095 on 1/24/15.
 */
public class SmartAi extends Connect4Player implements Connect4AI {
    ArrayList<Group> myWinningGroups, opponentWinningGroups;

    public SmartAi(Connect4Moderator mod, Color token) {
        super(mod, token);
        myWinningGroups = new ArrayList<Group>();
        opponentWinningGroups = new ArrayList<Group>();
    }

    private boolean isPlayable(int row, int col) {
        if (row == 5)
            return board[row][col].equals(Color.WHITE);
        return board[row][col].equals(Color.WHITE) && !board[row + 1][col].equals(Color.WHITE);
    }

    private int winningCol() {
//        System.out.println(mod.getPlayer(2).getToken());
        myWinningGroups.clear();
        opponentWinningGroups.clear();
        for (int i = 0; i < mod.groups.size(); i++) {
            if (mod.groups.get(i).getDangerous() != -1) {
//                System.out.println(mod.groups.get(i).getCriticalColor());
                if (mod.groups.get(i).getCriticalColor().equals(token)) {
                    myWinningGroups.add(mod.groups.get(i));
                }
                if (!mod.groups.get(i).getCriticalColor().equals(Color.WHITE) && !mod.groups.get(i).getCriticalColor().equals(token)) {
                    opponentWinningGroups.add(mod.groups.get(i));
                }
            }
        }

        System.out.println(opponentWinningGroups.size());
        if (myWinningGroups.size() > 0)
            return myWinningGroups.get(0).getDangerous() * myWinningGroups.get(0).dCol + myWinningGroups.get(0).startCol;
        if (opponentWinningGroups.size() > 0)
            return opponentWinningGroups.get(0).getDangerous() * opponentWinningGroups.get(0).dCol + opponentWinningGroups.get(0).startCol;
        return -1;
    }

    public int makePlay() {
        if (winningCol() != -1) {
            System.out.println("Playing critical column");
            if (myWinningGroups.size() == 0)
                System.out.println("Not on my watch");
            else
                System.out.println("I win!");
            return winningCol();
        } else {
            System.out.println("playing random");
            return getRandomPlay();
        }
    }
}