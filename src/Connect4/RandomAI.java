package Connect4;

/**
 * Created by 40095 on 1/24/15.
 */
public class RandomAI extends Connect4Player {

    public RandomAI(Connect4Moderator mod, char token) {
        super(mod, token);
    }

    public void makePlay() {
        mod.dropToken(getRandomPlay());
    }

    private int getRandomPlay() {
        int col = 0;
        boolean isValid = false;
        while (!isValid) {
            for (int i = 5; i > 0; i--) {
                if (board[i][col] != '.') {
                    isValid = true;
                }
            }
        }
        return col;
    }


}
