package views;

import java.util.Scanner;

public interface View {
    String COLOR_RESET = "\u001B[0m";
    String COLOR_RED = "\u001B[31m";
    String COLOR_GREEN = "\u001B[32m";
    String COLOR_YELLOW = "\u001B[33m";

    void display();

    default void writeRedLine(String line) {
        System.out.println(COLOR_RED + line + COLOR_RESET);
    }

    default void writeGreenLine(String line) {
        System.out.println(COLOR_GREEN + line + COLOR_RESET);
    }

    default void writeYellowLine(String line) {
        System.out.println(COLOR_YELLOW + line + COLOR_RESET);
    }

    default String getUserInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            writeRedLine("You have to enter something!");
        }
    }

    default int getNumberInputMinMax(int min, int max) {
        while (true) {
            System.out.println("Please enter a number between " + min + " and " + max);
            String input = getUserInput();

            try {
                int asNumber = Integer.parseInt(input);

                if (asNumber >= min && asNumber <= max) {
                    return asNumber;
                }

                writeRedLine("The number has to be between " + min + " and " + max + "!");
            } catch (NumberFormatException e) {
                writeRedLine("You have to enter a number!");
            }
        }
    }

    default int getNumberInput() {
        while (true) {
            System.out.println("Please enter a whole number");
            String input = getUserInput();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                writeRedLine("You have to enter a number!");
            }
        }
    }
}
