package Connect4;

import javafx.scene.paint.Color;

/**
 * Created by 40095 on 1/25/15.
 */
public abstract class Connect4Player {
    Connect4Moderator mod;
    Color[][] board;
    int width;
    int height;
    Color token;

    public Connect4Player(Connect4Moderator mod, Color token) {
        this.mod = mod;
        this.board = mod.getBoard();
        height = board.length;
        width = board[0].length;
        this.token = token;
    }

    public abstract int makePlay();
}
