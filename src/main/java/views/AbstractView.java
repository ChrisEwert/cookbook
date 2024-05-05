package views;

import java.util.List;
import java.util.Scanner;

public abstract class AbstractView implements View {
    String COLOR_RESET = "\u001B[0m";
    String COLOR_RED = "\u001B[31m";
    String COLOR_GREEN = "\u001B[32m";
    String COLOR_YELLOW = "\u001B[33m";

    public abstract void display();

    public void writeRedLine(String line) {
        System.out.println(COLOR_RED + line + COLOR_RESET);
    }

    public void writeGreenLine(String line) {
        System.out.println(COLOR_GREEN + line + COLOR_RESET);
    }

    public void writeYellowLine(String line) {
        System.out.println(COLOR_YELLOW + line + COLOR_RESET);
    }

    public String getUserInput() {
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

    public int getNumberInput() {
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

    public int getNumberInRange(int min, int max) {
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

    public String getNumberInRangeWithSpecialCases(int min, int max, List<String> exceptions) {
        StringBuilder optionBuilder = new StringBuilder();
        for (String option : exceptions) {
            optionBuilder
                .append("'")
                .append(option)
                .append("' ");
        }
        while (true) {
            System.out.println("Please enter a number between " + min + " and " + max + " or one of those options: " + optionBuilder);
            String input = getUserInput();

            for (String exception : exceptions) {
                if (input.equalsIgnoreCase(exception)) {
                    return input;
                }
            }

            try {
                int asNumber = Integer.parseInt(input);

                if (asNumber >= min && asNumber <= max) {
                    return String.valueOf(asNumber);
                }

                writeRedLine("The number has to be between " + min + " and " + max + "!");
            } catch (NumberFormatException e) {
                writeRedLine("You have to enter a number or one of those options: " + optionBuilder);
            }
        }
    }
}
