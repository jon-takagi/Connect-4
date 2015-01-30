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
        System.out.println("making random play");
        return getRandomPlay();
    }

}
