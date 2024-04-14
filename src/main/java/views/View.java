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
        System.out.println();
    }

    default void writeGreenLine(String line) {
        System.out.println(COLOR_GREEN + line + COLOR_RESET);
        System.out.println();
    }

    default void writeYellowLine(String line) {
        System.out.println(COLOR_YELLOW + line + COLOR_RESET);
    }

    default String getUserInput() {
        while (true) {
            System.out.print("Your input: ");
            String input = new Scanner(System.in).nextLine();
            if (!input.trim().isEmpty()) {
                return input;
            }
            writeRedLine("You have to enter something!");
        }
    }

    default int getNumberInput(int min, int max) {
        while (true) {
            System.out.println("Please enter a number between " + min + " and " + max);
            String input = getUserInput();
            try {
                int asNumber = Integer.parseInt(input);
                if (asNumber < min || asNumber > max) {
                    writeRedLine("The number has to be between " + min + " and " + max + "!");
                } else {
                    return asNumber;
                }
            } catch (NumberFormatException e) {
                writeRedLine("You have to enter a number!");
            }
        }
    }
}
