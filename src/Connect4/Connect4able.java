package Connect4;

import javafx.scene.paint.Color;

/**
 * Created by 40095 on 1/23/15.
 */
public interface Connect4able {
    void dropToken(int col);

    void clear();

    boolean hasWinner();

    Color getWinner();

    boolean isGameOver();

    Color currentPlayer();

}
