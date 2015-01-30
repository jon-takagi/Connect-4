package Connect4;

import javafx.scene.paint.Color;

/**
 * Created by 40095 on 1/24/15.
 */
public class SmartAi extends Connect4Player {

    public SmartAi(Connect4Moderator mod, Color token) {
        super(mod, token);
    }

    private int winningCol() {
        return -1;
    }

    public int makePlay() {
        if (winningCol() != -1) {
            System.out.println("ha");
            return winningCol();

        } else {
            System.out.println("playing random");
            return getRandomPlay();
        }
    }
}
