package pl.glownia.pamela;

import java.util.Scanner;

public class Printer implements PrintableProperties {
    Calculator calculator;

    public Printer() {
        calculator = new Calculator();
    }

    void printUserChoice() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a request: ");
        String userNumber = scan.nextLine();
        while (!(userNumber.equals("0"))) {
            if (userNumber.equals("")) {
                printWelcome();
            } else if (userNumber.contains(" ")) {
                String[] array = userNumber.split(" ");
                if (array.length == 2) {
                    long beginNumber = calculator.takeNumber(array, 0);
                    long counter = calculator.takeNumber(array, 1);
                    printProperties(beginNumber, counter);
                } else {
                    long beginNumber = calculator.takeNumber(array, 0);
                    long counter = calculator.takeNumber(array, 1);
                    String property = calculator.takeProperty(array, 2);
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

    @Override
    public void printProperties(long number) {
        if (calculator.isNatural(number)) {
            System.out.println("Properties of " + number);
            System.out.println("even:\t" + calculator.isEven(number));
            System.out.println("odd:\t" + calculator.isOdd(number));
            System.out.println("buzz:\t" + calculator.isBuzzNumber(number));
            System.out.println("duck:\t" + calculator.isDuck(number));
            System.out.println("palindromic:" + calculator.isPalindromic(number));
            System.out.println("gapful:\t" + calculator.isGapful(number));
            System.out.println("spy:\t" + calculator.isSpy(number));
            System.out.println("sunny:\t" + calculator.isSunny(number));
        }
    }

    @Override
    public void printProperties(long beginNumber, long counter) {
        if (calculator.isNatural(beginNumber) && calculator.isNatural(counter)) {
            for (long i = beginNumber; i < beginNumber + counter; i++) {
                printShortProperties(i);
            }
        }
    }

    @Override
    public void printProperties(long beginNumber, long counter, String userProperty) {
        boolean rightProperty = false;
        if (!(calculator.isNatural(counter))) {
            System.out.println("Second parameter should be a natural number");
        }
        if (calculator.isNatural(beginNumber) && calculator.isNatural(counter)) {
            for (Properties prop : Properties.values()) {
                if (prop.equals(userProperty)) {
                    rightProperty = true;
                    while (counter > 0) {
                        switch (prop) {
                            case BUZZ:
                                if (calculator.isBuzzNumber(beginNumber)) {
                                    printShortProperties(beginNumber);
                                    counter--;
                                }
                                break;
                            case DUCK:
                                if (calculator.isDuck(beginNumber)) {
                                    printShortProperties(beginNumber);
                                    counter--;
                                }
                                break;
                            case PALINDROMIC:
                                if (calculator.isPalindromic(beginNumber)) {
                                    printShortProperties(beginNumber);
                                    counter--;
                                }
                                break;
                            case GAPFUL:
                                if (calculator.isGapful(beginNumber)) {
                                    printShortProperties(beginNumber);
                                    counter--;
                                }
                                break;

                            case SPY:
                                if (calculator.isSpy(beginNumber)) {
                                    printShortProperties(beginNumber);
                                    counter--;
                                }
                                break;

                            case EVEN:
                                if (calculator.isEven(beginNumber)) {
                                    printShortProperties(beginNumber);
                                    counter--;
                                }
                                break;

                            case ODD:
                                if (calculator.isOdd(beginNumber)) {
                                    printShortProperties(beginNumber);
                                    counter--;
                                }
                                break;
                            case SUNNY:
                                if (calculator.isSunny(beginNumber)) {
                                    printShortProperties(beginNumber);
                                    counter--;
                                }
                                break;
                        }
                        beginNumber++;
                    }
                }
            }
            if (!rightProperty) {
                System.out.println("The property [" + userProperty + "] is wrong.\nAvailable properties: buzz, duck, palindromic, gapful, spy, even, odd, sunny");
            }
        }
    }

    void printShortProperties(long number) {
        System.out.print(number + " is ");
        if (calculator.isEven(number)) {
            System.out.print("even");
        }
        if (calculator.isOdd(number)) {
            System.out.print("odd");
        }
        if (calculator.isBuzzNumber(number)) {
            System.out.print(", buzz ");
        }
        if (calculator.isDuck(number)) {
            System.out.print(", duck");
        }
        if (calculator.isPalindromic(number)) {
            System.out.print(", palindromic");
        }
        if (calculator.isGapful(number)) {
            System.out.print(", gapful");
        }
        if (calculator.isSpy(number)) {
            System.out.print(", spy");
        }
        if (calculator.isSunny(number)) {
            System.out.print(", sunny");
        }
        System.out.println();
    }

    void run() {
        printWelcome();
        printUserChoice();
    }
}