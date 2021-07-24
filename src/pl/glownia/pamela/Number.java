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
        return (number % 2 == 0);
    }

    boolean isOdd(int number) {
        return (number % 2 != 0);
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

    int createReverseOfNumber(int number) {
        int reverse = 0;
        while (number != 0) {
            reverse = reverse * 10 + number % 10;
            number /= 10;
        }
        return reverse;
    }

    boolean isPalindromic(int number) {
        return createReverseOfNumber(number) == number;
    }

    void printProperties(int number) {
        System.out.println("Properties of " + number);
        System.out.println("\t\teven:\t" + isEven(number));
        System.out.println("\t\todd:\t" + isOdd(number));
        System.out.println("\t\tbuzz:\t" + isBuzzNumber(number));
        System.out.println("\t\tduck:\t" + isDuck(number));
        System.out.println("palindromic:\t" + isPalindromic(number));
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