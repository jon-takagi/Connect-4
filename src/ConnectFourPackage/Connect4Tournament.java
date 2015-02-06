package ConnectFourPackage;

import javafx.scene.paint.Color;

/**
 * Created by 40095 on 1/25/15.
 */
public class Connect4Tournament {
    public static void main(String[] args) {
        int games = 50;
        Color[][][] test = new Color[games][6][7];
        for (int i = 0; i < games; i++) {
            for (int j = 0; j < test[0].length; j++) {
                for (int k = 0; k < test[0][0].length; k++) {
                    test[i][j][k] = Color.WHITE;
                }
            }
        }
        Connect4Moderator[] mods = new Connect4Moderator[games];
        for (int i = 0; i < games; i++) {
            mods[i] = new Connect4Moderator(test[i], 0);
        }
        Color[] winners = new Color[games];
        for (int i = 0; i < games; i++) {
            winners[i] = mods[i].playGame();
            System.out.println("The winner of game " + i + " is " + winners[i]);
        }
        double xWins = 0, oWins = 0, draws = 0;
        for (int i = 0; i < games; i++) {
            if (winners[i].equals(Color.RED)) {
                System.out.println("");
                System.out.println("game " + i);
                mods[i].printBoard();
                System.out.println("X won at " + mods[i].winningGroup);
                xWins++;
            }
            if (winners[i].equals(Color.YELLOW))
                oWins++;
            if (winners[i].equals(Color.WHITE))
                draws++;
        }


        System.out.println("    X: " + xWins + ", "+  (xWins / games) * 100 + "%");
        System.out.println("    O: " + oWins + ", " + (oWins / games) * 100 + "%");
        System.out.println("Draws: " + draws + ", " + (draws / games) * 100 + "%");


    }
}
