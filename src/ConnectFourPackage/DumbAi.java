package ConnectFourPackage;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by 40095 on 2/5/15.
 */
public class DumbAi extends Connect4Player {
    ArrayList<Integer> validCols = new ArrayList<>();

    public DumbAi() {
    }


    @Override
    public int makePlay() {
        for (int i = 0; i < 7; i++) {
            if (board[0][i].equals(Color.WHITE)) {
                validCols.add(i);
            }
        }
        int colToPlay = validCols.get((int) (Math.random() * validCols.size()));
//        System.out.println("colToPlay = " + colToPlay);
        return colToPlay;

    }

}
