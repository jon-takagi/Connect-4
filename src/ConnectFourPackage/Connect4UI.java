package ConnectFourPackage;

import java.util.Scanner;

/**
 * Created by 40095 on 1/25/15.
 */
public class Connect4UI extends Connect4Player {
    Scanner keyboard;

    public Connect4UI() {
        keyboard = new Scanner(System.in);
    }

    public int makePlay() {
        System.out.println("Enter the column a-g to play in");
        String col = keyboard.nextLine();
        int c = Character.getNumericValue(col.charAt(0)) - 10;
        return c;
    }

}
