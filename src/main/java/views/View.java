package views;

import java.util.Scanner;

public interface View {
    String COLOR_RESET = "\u001B[0m";
    String COLOR_RED = "\u001B[31m";
    String COLOR_GREEN = "\u001B[32m";

    void display();

    default void writeRedLine(String line) {
        System.out.println(COLOR_RED + line + COLOR_RESET);
    }

    default void writeGreenLine(String line) {
        System.out.println(COLOR_GREEN + line + COLOR_RESET);
    }

    default String getUserInput() {
        while (true) {
            String input = new Scanner(System.in).nextLine();
            if (!input.trim().isEmpty()) {
                return input;
            }
            writeRedLine("Please enter something!");
        }

    }
}
