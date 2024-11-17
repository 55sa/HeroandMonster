package Util;

import Entity.Human.Hero;

import java.util.Scanner;

//tools for the whole program
public class Utils {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Validates an integer input within a specific range.
     *
     * @param prompt the message to display to the user
     * @param min    the minimum valid value (inclusive)
     * @param max    the maximum valid value (inclusive)
     * @return a valid integer within the specified range
     */
    public static int getIntInRange(String prompt, int min, int max) {
        int input;
        while (true) {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.printf("Please enter a number between %d and %d.%n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    /**
     * Validates string input against a set of allowed characters or strings.
     *
     * @param prompt      the message to display to the user
     * @param validInputs an array of allowed inputs (e.g., ["Y", "N"])
     * @return a valid string input from the allowed options
     */
    public static String getStringFromOptions(String prompt, String[] validInputs) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim().toUpperCase();
            for (String validInput : validInputs) {
                if (input.equals(validInput.toUpperCase())) {
                    return input;
                }
            }
            System.out.printf("Invalid input. Please enter one of the following options: %s%n", String.join(", ", validInputs));
        }
    }

    /**
     * Validates a yes/no input, returning true for 'Y' and false for 'N'.
     *
     * @param prompt the message to display to the user
     * @return true if the user enters 'Y', false if 'N'
     */
    public static boolean getYesNo(String prompt) {
        String[] validInputs = {"Y", "N"};
        String input = getStringFromOptions(prompt, validInputs);
        return input.equals("Y");
    }


}
