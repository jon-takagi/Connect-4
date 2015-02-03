package Connect4;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Created by 40095 on 1/25/15.
 */
public abstract class Connect4Player implements Connect4AI {
    Connect4Moderator mod;
    Color[][] board;
    Color token;

    public Connect4Player(Connect4Moderator mod, Color token) {
        this.mod = mod;
        this.board = mod.getBoard();
        this.token = token;
    }

    public abstract int makePlay();

    public boolean isValid(int col) {
        return board[0][col].equals(Color.WHITE);

    }

    public int getRandomPlay() {
        int col = (int) (Math.random() * 6);
        boolean isValid = isValid(col);
        while (!isValid) {
            col = (int) (Math.random() * 6);
            isValid = isValid(col);
        }
        return col;
    }

    public int getMove(Color[][] board) {
        this.board = board;
        return makePlay();
    }

    public Color getToken() {
        return token;
    }

    public Image getImage() {
        return null;
    }

    public String getName() {
        return toString();
    }

    public String toString() {
        return "Jon's fucking boss ass " + getClass();
    }

}