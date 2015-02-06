package ConnectFourPackage;

import javafx.scene.paint.Color;

/**
 * Created by 40095 on 1/24/15.
 */
public class RandomAI extends Connect4Player {

    public RandomAI() {
    }

    public int makePlay() {
//        System.out.println("making random play");
        return getRandomPlay();
    }

    @Override
    public int getMove(Color[][] board, Color me) {
        this.board = board;
        this.token = me;

        return makePlay();
    }
}
