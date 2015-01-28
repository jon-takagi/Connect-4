package Connect4;

/**
 * Created by 40095 on 1/25/15.
 */
public abstract class Connect4Player {
    Connect4Moderator mod;
    char[][] board;
    int width;
    int height;
    char token;

    public Connect4Player(Connect4Moderator mod, char token) {
        this.mod = mod;
        this.board = mod.getBoard();
        height = board.length;
        width = board[0].length;
        this.token = token;
    }
    public abstract void makePlay();
}
