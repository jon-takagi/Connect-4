package Connect4;

import javafx.scene.paint.Color;

/**
 * Created by 40095 on 1/26/15.
 */
public class Main {
    public static void main(String[] args) {
        Color[][] test = new Color[6][7];
        for (int j = 0; j < test.length; j++) {
            for (int k = 0; k < test[0].length; k++) {
                test[j][k] = Color.WHITE;
            }
        }
//        test[5][0] = Color.RED;
//        test[5][5] = Color.YELLOW;
//        test[5][4] = Color.RED;
//        test[5][3] = Color.YELLOW;
//        test[5][2] = Color.RED;
//        test[4][2] = Color.RED;
//        test[4][3] = Color.RED;
//        test[4][4] = Color.RED;
//        System.out.println(test.length);


        Connect4Moderator moderator = new Connect4Moderator(test, 2);
        System.out.println(moderator.player1.getClass() + " is: " + moderator.player1.token.toString() + "; Red / R");
        System.out.println(moderator.player2.getClass() + " is: " + moderator.player2.token.toString() + "; Yellow / Y");

        moderator.playGame();

        System.out.println("The winner is: " + moderator.getWinner());
    }
}