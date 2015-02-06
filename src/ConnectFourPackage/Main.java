package ConnectFourPackage;

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
//        test[5][1] = Color.RED;
//        test[5][2] = Color.RED;
//        test[5][3] = Color.RED;
//        test[5][4] = Color.RED;


        Connect4Moderator moderator = new Connect4Moderator(test, 0);
        moderator.playGame();

        System.out.println("The winner is: " + moderator.getWinner());
    }
}