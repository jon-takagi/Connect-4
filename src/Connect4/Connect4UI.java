package Connect4;

import javafx.scene.paint.Color;

import java.util.Scanner;

/**
 * Created by 40095 on 1/25/15.
 */
public class Connect4UI extends Connect4Player {
    Scanner keyboard;

    public Connect4UI(Connect4Moderator mod, Color token) {
        super(mod, token);
        keyboard = new Scanner(System.in);
    }

    public int makePlay() {
        System.out.println("Enter the column a-g to play in");
        mod.printBoard();
        String col = keyboard.nextLine();
        int c = Character.getNumericValue(col.charAt(0)) - 10;
        return c;
    }

}
