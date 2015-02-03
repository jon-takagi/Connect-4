package Connect4;

import javafx.scene.paint.Color;

/**
 * Created by 40095 on 1/27/15.
 */
public class Group {
    Connect4Moderator mod;
    int startCol, startRow, dRow, dCol;
    Color[] values = new Color[4];

    public Group(Connect4Moderator mod, int startRow, int startCol, int dCol, int dRow) {
        this.mod = mod;
        this.dRow = dRow;
        this.dCol = dCol;
        this.startCol = startCol;
        this.startRow = startRow;
//        System.out.println("ΔX = " + dRow + " ΔY = " + dCol);
//        System.out.println(getLocation());

        fillValues();

    }

    private void fillValues() {

        for (int i = 0; i < values.length; i++) {
            int col = startCol + i * dCol;
            int row = startRow + i * dRow;
            values[i] = mod.getBoard()[row][col];
        }
    }

    public int dangerousRow() {
        if (getDangerous() == -1)
            return -1;
        else
            return getDangerous() * dRow + startRow;
    }

    public int dangerousCol() {
        if (getDangerous() == -1)
            return -1;
        else
            return getDangerous() * dCol + startCol;
    }

    public boolean isFull() {
        return values[0].equals(values[1]) && values[2].equals(values[3]) && values[1].equals(values[2]);
    }

    public Color getFullColor() {
        if (isFull())
            return values[0];
        return Color.WHITE;
    }


    public String toString() {
        return "[" + startRow + "][" + startCol + "] to [" + (3 * dRow + startRow) + "][" + (3 * dCol + startCol) + "]";
    }

    public String getValString() {
        String vals = "";
        for (int i = 0; i < values.length; i++) {
            vals = vals + values[i] + ", ";
        }
        return vals;

    }

    /**
     * @return which of the spaces in the group is "dangerous" in that if filled, will grant victory to one player
     * Returns -1 if no columns are dangerous or all spaces are filled
     */
    public int getDangerous() {
        fillValues();
        //1 = 2 = 3, 0 = white return 0
        if (values[0].equals(Color.WHITE) && values[1].equals(values[2]) && values[2].equals(values[3]))
            return 0;
        //0 = 2 = 3, 1 = white return 1
        if (values[1].equals(Color.WHITE) && values[0].equals(values[2]) && values[2].equals(values[3]))
            return 1;
        //0 = 1 = 3, 2 = white return 2
        if (values[2].equals(Color.WHITE) && values[0].equals(values[1]) && values[1].equals(values[3]))
            return 2;
        //0 = 1 = 2, 3 = white return 3
        if (values[3].equals(Color.WHITE) && values[0].equals(values[1]) && values[1].equals(values[2]))
            return 3;
        return -1;
    }

    /**
     * @return the color of the player that will win if they play in the "dangerous" column to fill the group
     */

    public Color getCriticalColor() {

        if (getDangerous() == 0)
            return values[1];
        else if (getDangerous() == -1)
            return Color.WHITE;
        else
            return values[getDangerous() - 1];
    }
}
