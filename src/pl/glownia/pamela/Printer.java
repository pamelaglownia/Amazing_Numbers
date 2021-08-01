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
                if (array.length > 1) {
                    long beginNumber = calculator.takeNumber(array, 0);
                    if (!(calculator.isNatural(beginNumber))) {
                        System.out.println("The first parameter should be a natural number or zero. ");
                        break;
                    }
                    long counter = calculator.takeNumber(array, 1);
                    if (!(calculator.isNatural(counter))) {
                        System.out.println("The second parameter should be a natural number or zero. ");
                        break;
                    }
                    if (array.length >= 3) {
                        String firstUserProperty = calculator.takeProperty(array, 2);
                        if (array.length == 3 && !(Properties.checkIfContainsProps(firstUserProperty))) {
                            System.out.println("The property [" + firstUserProperty.toUpperCase() + "] is wrong.\nAvailable properties: buzz, duck, palindromic, gapful, spy, even, odd, square, sunny");
                        }
                        if (array.length == 4) {
                            String secondUserProperty = calculator.takeProperty(array, 3);
                            if (!(Properties.checkIfContainsProps(firstUserProperty) && Properties.checkIfContainsProps(secondUserProperty))) {
                                System.out.println("The properties [" + firstUserProperty.toUpperCase() + "," + secondUserProperty.toUpperCase() + "] are wrong.\n" +
                                        "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
                            } else if (Properties.checkIfContainsProps(secondUserProperty)) {
                                printProperties(beginNumber, counter, firstUserProperty, secondUserProperty);
                            }
                        } else {
                            if (Properties.checkIfContainsProps(firstUserProperty)) {
                                printProperties(beginNumber, counter, firstUserProperty);
                            }
                        }
                    } else {
                        printProperties(beginNumber, counter);
                    }
                }
            } else {
                long number = Long.parseLong(userNumber);
                if (!(calculator.isNatural(number))) {
                    System.out.println("The first parameter should be a natural number or zero. ");
                } else {
                    printProperties(number);
                }
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
        System.out.println("Properties of " + number);
        System.out.println("even:\t" + calculator.isEven(number));
        System.out.println("odd:\t" + calculator.isOdd(number));
        System.out.println("buzz:\t" + calculator.isBuzzNumber(number));
        System.out.println("duck:\t" + calculator.isDuck(number));
        System.out.println("palindromic:" + calculator.isPalindromic(number));
        System.out.println("gapful:\t" + calculator.isGapful(number));
        System.out.println("spy:\t" + calculator.isSpy(number));
        System.out.println("square:\t" + calculator.isPerfectSquare(number));
        System.out.println("sunny:\t" + calculator.isSunny(number));
    }

    @Override
    public void printProperties(long beginNumber, long counter) {
        for (long i = beginNumber; i < beginNumber + counter; i++) {
            printShortProperties(i);
        }
    }

    @Override
    public void printProperties(long beginNumber, long counter, String userProperty) {
        for (Properties prop : Properties.values()) {
            if (prop.equals(userProperty)) {
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
                        case SQUARE:
                            if (calculator.isPerfectSquare(beginNumber)) {
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
    }

    @Override
    public void printProperties(long beginNumber, long counter, String firstProperty, String secondProperty) {
        if (firstProperty.equalsIgnoreCase(secondProperty)) {
            printProperties(beginNumber, counter, firstProperty);
        }
        if (Properties.ODD.equals(firstProperty) && Properties.EVEN.equals(secondProperty) || Properties.DUCK.equals(firstProperty) && Properties.SPY.equals(secondProperty) || Properties.SUNNY.equals(firstProperty) && Properties.SQUARE.equals(secondProperty)) {
            System.out.println("The request contains mutually exclusive properties:[" + firstProperty + ", " + secondProperty + "]\nThere are no numbers with these properties.");
        }
    }

    void printShortProperties(long number) {
        //using ternary operator  (booleanExpression ? expression1 : expression2) => condensed if statement
        StringBuilder numberProperties = new StringBuilder(number + " is ");
        numberProperties.append(calculator.isEven(number) ? "even" : "");
        numberProperties.append(calculator.isOdd(number) ? " odd" : "");
        numberProperties.append(calculator.isBuzzNumber(number) ? ", buzz" : "");
        numberProperties.append(calculator.isDuck(number) ? ", duck" : "");
        numberProperties.append(calculator.isPalindromic(number) ? ", palindromic" : "");
        numberProperties.append(calculator.isGapful(number) ? ", gapful" : "");
        numberProperties.append(calculator.isSpy(number) ? ", spy" : "");
        numberProperties.append(calculator.isPerfectSquare(number) ? ", square" : "");
        numberProperties.append(calculator.isSunny(number) ? ", sunny" : "");
        System.out.println(numberProperties);
    }

    void run() {
        printWelcome();
        printUserChoice();
    }
}