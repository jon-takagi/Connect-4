package Connect4;

import java.util.Scanner;

/**
 * Created by 40095 on 1/25/15.
 */
public class Connect4UI extends Connect4Player {

    public Connect4UI(Connect4Moderator mod, char token) {
        super(mod, token);
    }

    public void makePlay() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the column a-g to play in");
        mod.printBoard();
        String col = keyboard.nextLine();
        int c = Character.getNumericValue(col.charAt(0)) - 10;
        mod.dropToken(c);
        return;
    }



}
