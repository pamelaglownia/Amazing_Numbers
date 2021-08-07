package pl.glownia.pamela;

import java.util.ArrayList;
import java.util.Locale;
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
                printProperties(array);

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
        System.out.println("- two natural numbers and properties to search for");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }

    @Override
    public void printProperties(long number) {
        System.out.printf(Locale.ENGLISH, "Properties of %,d\n", number);
        System.out.printf("%12s: %s%n", "even", calculator.isEven(number));
        System.out.printf("%12s: %s%n", "odd", calculator.isOdd(number));
        System.out.printf("%12s: %s%n", "buzz", calculator.isBuzzNumber(number));
        System.out.printf("%12s: %s%n", "duck", calculator.isDuck(number));
        System.out.printf("%12s: %s%n", "palindromic", calculator.isPalindromic(number));
        System.out.printf("%12s: %s%n", "gapful", calculator.isGapful(number));
        System.out.printf("%12s: %s%n", "spy", calculator.isSpy(number));
        System.out.printf("%12s: %s%n", "square", calculator.isPerfectSquare(number));
        System.out.printf("%12s: %s%n", "sunny", calculator.isSunny(number));
        System.out.printf("%12s: %s%n", "jumping", calculator.isJumping(number));
    }

    @Override
    public void printProperties(long beginNumber, long counter) {
        for (long i = beginNumber; i < beginNumber + counter; i++) {
            printShortProperties(i);
        }
    }

    boolean checkRightPropertiesToPrint(long beginNumber, String userProperty) {
        for (Properties prop : Properties.values()) {
            if (prop.equals(userProperty)) {
                switch (prop) {
                    case BUZZ:
                        if (calculator.isBuzzNumber(beginNumber)) {
                            return true;
                        }
                        break;
                    case DUCK:
                        if (calculator.isDuck(beginNumber)) {
                            return true;
                        }
                        break;
                    case PALINDROMIC:
                        if (calculator.isPalindromic(beginNumber)) {
                            return true;
                        }
                        break;
                    case GAPFUL:
                        if (calculator.isGapful(beginNumber)) {
                            return true;
                        }
                        break;

                    case SPY:
                        if (calculator.isSpy(beginNumber)) {
                            return true;
                        }
                        break;

                    case EVEN:
                        if (calculator.isEven(beginNumber)) {
                            return true;
                        }
                        break;

                    case ODD:
                        if (calculator.isOdd(beginNumber)) {
                            return true;
                        }
                        break;
                    case SQUARE:
                        if (calculator.isPerfectSquare(beginNumber)) {
                            return true;
                        }
                        break;
                    case SUNNY:
                        if (calculator.isSunny(beginNumber)) {
                            return true;
                        }
                        break;
                    case JUMPING:
                        if (calculator.isJumping(beginNumber)) {
                            return true;
                        }
                        break;
                }
            }
        }
        return false;
    }

    boolean isWrongProperty(String userChoice) {
        int counter = 0;
        for (Properties prop : Properties.values()) {
            if (!(prop.equals(userChoice))) {
                counter += 1;
            }
        }
        return counter == Properties.values().length;
    }

    boolean isMutuallyExclusive(String userChoice, String[] array) {
        String mutuallyExclusive = null;
        for (int i = 3; i < array.length; i++) {
            if (Properties.ODD.equals(userChoice) && Properties.EVEN.equals(array[i]) || Properties.EVEN.equals(userChoice) && Properties.ODD.equals(array[i]) || Properties.DUCK.equals(userChoice) && Properties.SPY.equals(array[i]) || Properties.SPY.equals(userChoice) && Properties.DUCK.equals(array[i]) || Properties.SUNNY.equals(userChoice) && Properties.SQUARE.equals(array[i]) || Properties.SQUARE.equals(userChoice) && Properties.SUNNY.equals(array[i])) {
                mutuallyExclusive = array[i];
            }
        }
        if (mutuallyExclusive != null) {
            System.out.println("The request contains mutually exclusive properties:[" + userChoice + ", " + mutuallyExclusive + "]\nThere are no numbers with these properties.");
            return true;
        }
        return false;
    }

    void printWrongProperties(ArrayList<String> wrongProperties) {
        if (wrongProperties.size() == 1) {
            System.out.println("The property [" + wrongProperties.get(0) + "] is wrong.");
        } else if (wrongProperties.size() > 1) {
            System.out.print("The properties: [");
            for (String wrongProperty : wrongProperties) {
                System.out.print(wrongProperty + " ");
            }
            System.out.print("] are wrong.");
        }
        System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
    }

    @Override
    public void printProperties(String[] array) {
        long beginNumber = calculator.takeNumber(array, 0);
        if (!(calculator.isNatural(beginNumber))) {
            System.out.println("The first parameter should be a natural number or zero. ");
        }
        long numberCounter = calculator.takeNumber(array, 1);
        if (!(calculator.isNatural(numberCounter))) {
            System.out.println("The second parameter should be a natural number or zero. ");
        } else {
            if (array.length == 2) {
                printProperties(beginNumber, numberCounter);
            } else if (array.length > 2) {
                while (numberCounter > 0) {
                    boolean correct = true;
                    boolean mutuallyExclusive = false;
                    int arrayElementsToValidate = array.length - 2;
                    int countRightProps = 0;
                    ArrayList<String> wrongProperties = new ArrayList<>();
                    for (int j = 2; j < array.length; j++) {
                        String userChoice = calculator.takeProperty(array, j);
                        if (isWrongProperty(userChoice)) {
                            wrongProperties.add(userChoice);
                        } else if (array.length >= 4 && isMutuallyExclusive(userChoice, array)) {
                            mutuallyExclusive = true;
                        } else {
                            if (checkRightPropertiesToPrint(beginNumber, userChoice)) {
                                countRightProps += 1;
                            } else {
                                correct = false;
                            }
                        }
                    }
                    if (correct && arrayElementsToValidate == countRightProps) {
                        printShortProperties(beginNumber);
                        numberCounter--;
                    }
                    beginNumber++;

                    if (wrongProperties.size() >= 1) {
                        printWrongProperties(wrongProperties);
                        break;
                    } else if (mutuallyExclusive) {
                        break;
                    }
                }
            }
        }
    }

    void printShortProperties(long number) {
        //using ternary operator  (booleanExpression ? expression1 : expression2) => condensed if statement
        StringBuilder numberProperties = new StringBuilder(String.format(Locale.ENGLISH, "%,d", number) + " is ");
        numberProperties.append(calculator.isEven(number) ? "even" : "");
        numberProperties.append(calculator.isOdd(number) ? " odd" : "");
        numberProperties.append(calculator.isBuzzNumber(number) ? ", buzz" : "");
        numberProperties.append(calculator.isDuck(number) ? ", duck" : "");
        numberProperties.append(calculator.isPalindromic(number) ? ", palindromic" : "");
        numberProperties.append(calculator.isGapful(number) ? ", gapful" : "");
        numberProperties.append(calculator.isSpy(number) ? ", spy" : "");
        numberProperties.append(calculator.isPerfectSquare(number) ? ", square" : "");
        numberProperties.append(calculator.isSunny(number) ? ", sunny" : "");
        numberProperties.append(calculator.isJumping(number) ? ", jumping" : "");
        System.out.println(numberProperties);
    }

    void run() {
        printWelcome();
        printUserChoice();
    }
}