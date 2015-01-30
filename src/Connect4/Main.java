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

        Connect4Moderator moderator = new Connect4Moderator(test, 0);
        System.out.println(moderator.player1.getClass() + " is: " + moderator.player1.token);
        System.out.println(moderator.player2.getClass() + " is: " + moderator.player2.token);
//        moderator.printBoard();
        moderator.playGame();
        moderator.printBoard();


//        System.out.println("The winner is: " + moderator.getWinner());

    }
}
