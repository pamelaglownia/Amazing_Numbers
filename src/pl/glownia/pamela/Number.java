package pl.glownia.pamela;

import java.util.Scanner;

public class Number {

    boolean isNatural(int number) {
        if (number > 0) {
            return true;
        } else {
            System.out.println("This number is not natural!");
            return false;
        }
    }

    boolean isEven(int number) {
        return number % 2 == 0;
    }

    boolean isOdd(int number) {
        return number % 2 != 0;
    }

    boolean isBuzzNumber(int number) {
        return number % 7 == 0 || number % 10 == 7;
    }

    boolean isDuck(int number) {
        String temp = Integer.toString(number);
        for (int i = 1; i < temp.length(); i++) {
            if (temp.charAt(i) == '0') {
                return true;
            }
        }
        return false;
    }

    void printProperties(int number) {
        System.out.println("Properties of " + number);
        System.out.println("\teven:\t" + isEven(number));
        System.out.println("\todd:\t" + isOdd(number));
        System.out.println("\tbuzz:\t" + isBuzzNumber(number));
        System.out.println("\tduck:\t" + isDuck(number));
    }

    void checkUserNumber() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        int number = scan.nextInt();
        if (isNatural(number)) {
            printProperties(number);
        }
    }
}