package Connect4;

/**
 * Created by 40095 on 1/27/15.
 */
public class Group {
    Connect4Moderator mod;
    int startX, startY, incrementX, incrementY;
    char[] values = new char[4];

    public Group(Connect4Moderator mod, int incrementX, int incrementY, int startX, int startY) {
        this.mod = mod;
        this.incrementX = incrementX;
        this.incrementY = incrementY;
        this.startX = startX;
        this.startY = startY;

        for (int i = 0; i < values.length; i++) {
            values[i] = 'X';
            mod.getBoard();
            System.out.println(mod.getBoard());
//            values[i] = mod.getBoard()[startX + i * incrementX][startY + i * incrementY];
        }

    }

    public String getLocation() {
        return "[" + startY + "][" + startX + "] to [" + (3 * incrementY + startY) + "][" + (3 * incrementY + startY) + "]";
    }

    public int getDangerous() {
        return 0;
    }
}
