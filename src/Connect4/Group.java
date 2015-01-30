package Connect4;

/**
 * Created by 40095 on 1/27/15.
 */
public class Group {
    Connect4Moderator mod;
    int startCol, startRow, dRow, dCol;
    char[] values = new char[4];

    public Group(Connect4Moderator mod, int startRow, int startCol, int dCol, int dRow) {
        this.mod = mod;
        this.dRow = dRow;
        this.dCol = dCol;
        this.startCol = startCol;
        this.startRow = startRow;
//        System.out.println("ΔX = " + dRow + " ΔY = " + dCol);
//        System.out.println(getLocation());

        for (int i = 0; i < values.length; i++) {
            int col = startCol + i * dCol;
            int row = startRow + i * dRow;
//            System.out.println("retrieving value: " + i);
//            System.out.println("Row value: " + row);
//            System.out.println("Col value: " + col);
//            System.out.println(mod.getBoard()[row][col]);
            values[i] = mod.getBoard()[row][col];
//            System.out.println("value = " + values[i]);
        }

    }

    public String getLocation() {
        return "[" + startRow + "][" + startCol + "] to [" + (3 * dCol + startRow) + "][" + (3 * dCol + startRow) + "]";
    }

    public int getDangerous() {
        return 0;
    }
}
