package Connect4;

/**
 * Created by 40095 on 1/24/15.
 */
public class SmartAi extends Connect4Player {

    public SmartAi(Connect4Moderator mod, char token) {
        super(mod, token);
    }

    private int randomPlay() {
        int col = 0;
        boolean isValid = false;
        while (!isValid) {
            col = (int) (Math.random() * 6);
            for (int i = 5; i > 0; i--) {
                if (board[i][col] != '.') {
                    isValid = true;
                }
            }
        }
        return col;
    }

    private int winningCol() {
        Connect4Moderator tester;
        for (int i = 0; i < 7; i++) {
            tester = new Connect4Moderator(mod.cloneBoard());
            tester.dropToken(i);
            if (tester.getWinner() == token) {
                return i;
            }
        }
        return -1;
    }

    private boolean isPlayable(int row, int col) {
        for (int i = 5; i >= 0; i--) {
            if (board[i][col] == '.') {
                return i == row;
            }
        }
        return false;
    }

    public void makePlay() {
        if (winningCol() != -1) {
            System.out.println("ha");
            mod.dropToken(winningCol());

        } else {
            System.out.println("playing random");
            mod.dropToken(randomPlay());
        }
    }
}
