package pl.glownia.pamela;

import java.util.Scanner;

public class Number {

    boolean isNatural(long number) {
        if (number >= 0) {
            return true;
        } else {
            System.out.println("The first parameter should be a natural number or zero.");
            return false;
        }
    }

    boolean isEven(long number) {
        return (number % 2 == 0);
    }

    boolean isOdd(long number) {
        return (number % 2 != 0);
    }

    boolean isBuzzNumber(long number) {
        return number % 7 == 0 || number % 10 == 7;
    }

    boolean isDuck(long number) {
        String temp = Long.toString(number);
        for (int i = 1; i < temp.length(); i++) {
            if (temp.charAt(i) == '0') {
                return true;
            }
        }
        return false;
    }

    long createReverseOfNumber(long number) {
        long reverse = 0L;
        while (number != 0) {
            reverse = reverse * 10 + number % 10;
            number /= 10;
        }
        return reverse;
    }

    boolean isPalindromic(long number) {
        return createReverseOfNumber(number) == number;
    }

    void printProperties(long number) {
        System.out.println("Properties of " + number);
        System.out.println("\t\teven:\t" + isEven(number));
        System.out.println("\t\todd:\t" + isOdd(number));
        System.out.println("\t\tbuzz:\t" + isBuzzNumber(number));
        System.out.println("\t\tduck:\t" + isDuck(number));
        System.out.println("palindromic:\t" + isPalindromic(number));
    }

    void checkUserNumber() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a request: ");
        long number = scan.nextLong();
        while (number != 0L) {
            if (isNatural(number)) {
                printProperties(number);
            }
            System.out.print("Enter a request: ");
            number = scan.nextLong();
        }
        System.out.println("Goodbye!");
    }

    void printWelcome() {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties,");
        System.out.println("- enter 0 to exit.");
    }

    void run() {
        printWelcome();
        checkUserNumber();
    }
}