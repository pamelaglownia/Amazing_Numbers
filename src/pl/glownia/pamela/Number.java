package pl.glownia.pamela;

import java.util.Arrays;
import java.util.Scanner;

public class Number implements PrintableProperties {

    boolean isNatural(long number) {
        if (number >= 0) {
            return true;
        } else {
            System.out.println("The parameters should be a natural number or zero.");
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

    boolean isGapful(long number) {
        if (number >= 100) {
            String temp = Long.toString(number);
            long firstDigit = Character.getNumericValue(temp.charAt(0));
            long secondDigit = Character.getNumericValue(temp.charAt(temp.length() - 1));
            long divisor = firstDigit * 10 + secondDigit;
            return number % divisor == 0;
        }
        return false;
    }

    boolean isSpy(long number) {
        long temp, sum = 0;
        long product = 1;
        while (number > 0) {
            temp = number % 10;
            sum += temp;
            product *= temp;
            number /= 10;
        }
        return product == sum;
    }

    @Override
    public void printProperties(long number) {
        if (isNatural(number)) {
            System.out.println("Properties of " + number);
            System.out.println("even:\t" + isEven(number));
            System.out.println("odd:\t" + isOdd(number));
            System.out.println("buzz:\t" + isBuzzNumber(number));
            System.out.println("duck:\t" + isDuck(number));
            System.out.println("palindromic:" + isPalindromic(number));
            System.out.println("gapful:\t" + isGapful(number));
            System.out.println("spy:\t" + isSpy(number));
        }

    }

    @Override
    public void printProperties(long beginNumber, long counter) {
        if (isNatural(beginNumber) && isNatural(counter)) {
            for (long i = beginNumber; i < beginNumber + counter; i++) {
                System.out.print(i + " is ");
                if (isEven(i)) {
                    System.out.print("even");
                }
                if (isOdd(i)) {
                    System.out.print("odd");
                }
                if (isBuzzNumber(i)) {
                    System.out.print(", buzz ");
                }
                if (isDuck(i)) {
                    System.out.print(", duck");
                }
                if (isPalindromic(i)) {
                    System.out.print(", palindromic");
                }
                if (isGapful(i)) {
                    System.out.print(", gapful");
                }
                if (isSpy(i)) {
                    System.out.print(", spy");
                }
                System.out.println();
            }
        }
    }

    @Override
    public void printProperties(long beginNumber, long counter, String userProperty) {
        boolean rightProperty = false;
        if (isNatural(beginNumber) && isNatural(counter)) {
            String[] array = {"buzz", "duck", "palindromic", "gapful", "spy", "even", "odd"};
            for (int j = 0; j < array.length; j++) {
                if (userProperty.equals(array[j])) {
                    rightProperty = true;
                    for (long i = beginNumber; i < beginNumber + counter; i++) {
                        switch (userProperty) {
                            case "buzz":
                                if (isBuzzNumber(i)) {
                                    printShortProperties(i);
                                }
                                break;
                            case "duck":
                                if (isDuck(i)) {
                                    printShortProperties(i);
                                }
                                break;
                            case "palindromic":
                                if (isPalindromic(i)) {
                                    printShortProperties(i);
                                }
                                break;
                            case "gapful":
                                if (isGapful(i)) {
                                    printShortProperties(i);
                                }
                                break;
                            case "spy":
                                if (isSpy(i)) {
                                    printShortProperties(i);
                                }
                                break;
                            case "even":
                                if (isEven(i)) {
                                    printShortProperties(i);
                                }
                                break;
                            case "odd":
                                if (isOdd(i)) {
                                    printShortProperties(i);
                                }
                                break;
                        }
                    }
                }
            }
            if (!rightProperty) {
                System.out.println("The property [" + userProperty + "] is wrong.\nAvailable properties: " + Arrays.toString(array));
            }
        }
    }

    void printShortProperties(long number) {
        System.out.print(number + " is ");
        if (isEven(number)) {
            System.out.print("even");
        }
        if (isOdd(number)) {
            System.out.print("odd");
        }
        if (isBuzzNumber(number)) {
            System.out.print(", buzz ");
        }
        if (isDuck(number)) {
            System.out.print(", duck");
        }
        if (isPalindromic(number)) {
            System.out.print(", palindromic");
        }
        if (isGapful(number)) {
            System.out.print(", gapful");
        }
        if (isSpy(number)) {
            System.out.print(", spy");
        }
        System.out.println();
    }

    void checkUserNumber() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a request: ");
        String userNumber = scan.nextLine();
        while (!(userNumber.equals("0"))) {
            if (userNumber.equals("")) {
                printWelcome();
            } else if (userNumber.contains(" ")) {
                String[] array = userNumber.split(" ");
                if (array.length == 2) {
                    long beginNumber = Long.parseLong(array[0]);
                    long counter = Long.parseLong(array[1]);
                    printProperties(beginNumber, counter);
                } else {
                    long beginNumber = Long.parseLong(array[0]);
                    long counter = Long.parseLong(array[1]);
                    String property = array[2];
                    printProperties(beginNumber, counter, property);
                }
            } else {
                long number = Long.parseLong(userNumber);
                printProperties(number);
            }
            System.out.print("Enter a request: ");
            userNumber = scan.nextLine();
        }
        System.out.println("Goodbye!");
    }

    void printWelcome() {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("\t *the first parameter represents a starting number;");
        System.out.println("\t *the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }

    void run() {
        printWelcome();
        checkUserNumber();
    }
}