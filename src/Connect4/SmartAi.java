package Connect4;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by 40095 on 1/24/15.
 */
public class SmartAi extends Connect4Player implements Connect4AI {
    ArrayList<Group> winningGroups, losingGroups, futureThreats;

    /*
    Order of precedence for plays to make:
    1) plays that make you win immediately
    2) plays that keep you from losing immediately
    3) random
    do not make plays that make a dangerous group playable
        if tiles below space = 1 don't do the thing

        for each future threat, check if playing in that column is viable
        put viable columns in a list, pick from them randomly
     */

    public SmartAi(Connect4Moderator mod, Color token) {
        super(mod, token);
        winningGroups = new ArrayList<Group>();
        losingGroups = new ArrayList<Group>();
        futureThreats = new ArrayList<Group>();
    }

    private boolean isPlayable(int row, int col) {
        if (row == 5)
            return board[row][col].equals(Color.WHITE);
        return board[row][col].equals(Color.WHITE) && !board[row + 1][col].equals(Color.WHITE);
    }

    private int tilesBelowSpace(int row, int col) {
        if (isPlayable(row, col))
            return 0;
        int count = 0;
        for (int i = row; i < 5; i++) {
            if (board[i][col].equals(Color.WHITE)) {
                count++;
            }
        }
        return count;
    }

    private int winningCol() {
//        System.out.println(mod.getPlayer(2).getToken());
        winningGroups.clear();
        losingGroups.clear();

        for (int i = 0; i < mod.groups.size(); i++) {
            if (mod.groups.get(i).getDangerous() != -1) {    //if the group is dangerous
                if (!isPlayable(mod.groups.get(i).dangerousRow(), mod.groups.get(i).dangerousCol())) //if the group is not playable
                    futureThreats.add(mod.groups.get(i));                                              //add it to "future threats"
                else {
                    //if the group is playable & dangerous decide if it will make me win or lose & put it in that group
                    if (mod.groups.get(i).getCriticalColor().equals(token)) {
                        winningGroups.add(mod.groups.get(i));
                    }
                    if (!mod.groups.get(i).getCriticalColor().equals(Color.WHITE) && !mod.groups.get(i).getCriticalColor().equals(token)) {
                        losingGroups.add(mod.groups.get(i));
                    }
                }
            }
        }

        if (winningGroups.size() > 0) {
            return winningGroups.get(0).dangerousCol();
        }
        if (losingGroups.size() > 0)
            return losingGroups.get(0).dangerousCol();
        return -1;
    }

    public int makePlay() {
        if (winningCol() != -1) {
            System.out.println("Playing critical column");
            if (winningGroups.size() == 0)
                System.out.println("Not on my watch");
            else
                System.out.println("I win!");
            return winningCol();
        } else {
            System.out.println("playing random");
            return getRandomPlay();
        }
    }

    @Override
    public int getRandomPlay() {
        int col = (int) (Math.random() * 6);
        boolean isValid = isValid(col);
        boolean isViable = false;
        for (int i = 0; i < futureThreats.size(); i++) {
            if (tilesBelowSpace(futureThreats.get(i).dangerousRow(), futureThreats.get(i).dangerousCol()) % 2 == 0) {
                isViable = true;
            }
        }
        while (!isValid && !isViable) {
            col = (int) (Math.random() * 6);
            isValid = isValid(col);
            for (int i = 0; i < futureThreats.size(); i++) {
                if (tilesBelowSpace(futureThreats.get(i).dangerousRow(), futureThreats.get(i).dangerousCol()) % 2 == 0) {
                    isViable = true;
                }
            }
        }
        return col;
    }
}