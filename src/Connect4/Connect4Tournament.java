package Connect4;

/**
 * Created by 40095 on 1/25/15.
 */
public class Connect4Tournament {
    public static void main(String[] args) {
        int games = 10000;
        char[][][] test = new char[games][6][7];
        for (int i = 0; i < games; i++) {
            for (int j = 0; j < test[0].length; j++) {
                for (int k = 0; k < test[0][0].length; k++) {
                    test[i][j][k] = '.';
                }
            }
        }
        Connect4Moderator[] mods = new Connect4Moderator[games];
        for (int i = 0; i < games; i++) {
            mods[i] = new Connect4Moderator(test[i], 0);
        }
        char[] winners = new char[games];
        for (int i = 0; i < games; i++) {
            winners[i] = mods[i].playGame();
        }
        double xWins = 0, oWins = 0, draws = 0;
        for (int i = 0; i < games; i++) {
            switch (winners[i]) {
                case 'X' :
                    xWins++;
                    break;
                case 'O' :
                    oWins++;
                    break;
                case '.' :
                    draws++;
                    break;
            }
        }
        System.out.println("    X: " + xWins + ", "+  (xWins / games) * 100 + "%");
        System.out.println("    O: " + oWins + ", " + (oWins / games) * 100 + "%");
        System.out.println("Draws: " + draws + ", " + (draws / games) * 100 + "%");


    }
}
