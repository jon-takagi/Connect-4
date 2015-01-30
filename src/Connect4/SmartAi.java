package Connect4;

import javafx.scene.paint.Color;

/**
 * Created by 40095 on 1/24/15.
 */
public class SmartAi extends Connect4Player {

    public SmartAi(Connect4Moderator mod, Color token) {
        super(mod, token);
    }

    private int randomPlay() {
        int col = 0;
        boolean isValid = false;
        while (!isValid) {
            col = (int) (Math.random() * 6);
            for (int i = 5; i > 0; i--) {
                if (!board[i][col].equals(Color.WHITE)) {
                    isValid = true;
                }
            }
        }
        return col;
    }

    private int winningCol() {
        return -1;
    }

    private boolean isPlayable(int row, int col) {
        for (int i = 5; i >= 0; i--) {
            if (!board[i][col].equals(Color.WHITE)) {
                return i == row;
            }
        }
        return false;
    }

    public int makePlay() {
        if (winningCol() != -1) {
            System.out.println("ha");
            return winningCol();

        } else {
            System.out.println("playing random");
            return randomPlay();
        }
    }
}
