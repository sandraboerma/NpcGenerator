package com.boerma.npcgenerator.utility;

import java.util.Scanner;

public class InputUtility {

    private static final Scanner scanner = new Scanner(System.in);

    private InputUtility() {
    }

    public static int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public static void closeScanner() {
        scanner.close();
    }
}
