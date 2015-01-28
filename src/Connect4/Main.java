package Connect4;

/**
 * Created by 40095 on 1/26/15.
 */
public class Main {
    public static void main(String[] args) {
        char[][] test = new char[6][7];
        for (int j = 0; j < test.length; j++) {
            for (int k = 0; k < test[0].length; k++) {
                test[j][k] = '.';
            }
        }
        Connect4Moderator moderator = new Connect4Moderator(test, 1);
        System.out.println(moderator.player1.getClass() + " is: " + moderator.player1.token);
        System.out.println(moderator.player2.getClass() + " is: " + moderator.player2.token);
        while(moderator.getWinner() == '.') {
            moderator.playGame();
        }
        moderator.printBoard();
        System.out.println("The winner is: " + moderator.getWinner());

    }
}
