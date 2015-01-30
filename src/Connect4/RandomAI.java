package Connect4;

import javafx.scene.paint.Color;

/**
 * Created by 40095 on 1/24/15.
 */
public class RandomAI extends Connect4Player {

    public RandomAI(Connect4Moderator mod, Color token) {
        super(mod, token);
    }

    public int makePlay() {
        return getRandomPlay();
    }

    private int getRandomPlay() {
        int col = 0;
        boolean isValid = false;
        while (!isValid) {
            for (int i = 5; i > 0; i--) {
                if (!board[i][col].equals(Color.WHITE)) {
                    isValid = true;
                }
            }
        }
        return col;
    }


}
