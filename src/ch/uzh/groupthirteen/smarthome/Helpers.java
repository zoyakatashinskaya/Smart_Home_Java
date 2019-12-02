package ch.uzh.groupthirteen.smarthome;

import java.util.Scanner;

/**
 * Collection of some Helpers used in all our classes
 */
public class Helpers {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Gets an phone-input, validated
     * @return A integer between 0 and 9
     */
    public static int getPhoneInput() {
        int input;
        while (true) {
            input = getInput();
            if (0 <= input && 9 >= input) {
                return input;
            } else {
                System.out.println("Uhm, I never saw a " + input + " button on a phone.");
            }
        }
    }

    /**
     * Gets a positive Integer
     * @return positive integer
     */
    public static int getPositiveNumber() {
        int input;
        while (true) {
            input = getInput();
            if (0 < input) {
                return input;
            } else {
                System.out.println("Zero and negative numbers are not allowed!");
            }
        }
    }

    /**
     * Gets an integer input
     * @return An input parsed as integer
     */
    private static int getInput() {

        while (true) {
            System.out.print(">>");
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Not a numerical, please try again!");
                scanner.next();
            }
        }
    }

}
