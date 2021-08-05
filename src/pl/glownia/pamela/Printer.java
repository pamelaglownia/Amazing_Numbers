package pl.glownia.pamela;

import java.util.ArrayList;
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
        System.out.println("jumping:\t" + calculator.isJumping(number));
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
        System.out.println(" Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
    }

    @Override
    public void printProperties(String[] array) {
        long beginNumber = calculator.takeNumber(array, 0);
        if (!(calculator.isNatural(beginNumber))) {
            System.out.println("The first parameter should be a natural number or zero. ");
        }
        long counter = calculator.takeNumber(array, 1);
        if (!(calculator.isNatural(counter))) {
            System.out.println("The second parameter should be a natural number or zero. ");
        } else {
            if (array.length == 2) {
                printProperties(beginNumber, counter);
            } else if (array.length > 2) {
                while (counter > 0) {
                    boolean flag;
                    int arrayElements = array.length - 2;
                    int countProps = 0;
                    ArrayList<String> wrongProperties = new ArrayList<>();
                    for (int j = 2; j < array.length; j++) {
                        String userChoice = calculator.takeProperty(array, j);
                        if (isWrongProperty(userChoice)) {
                            wrongProperties.add(userChoice);
                        } else {
                            if (checkRightPropertiesToPrint(beginNumber, userChoice)) {
                                countProps += 1;
                                flag = true;
                            } else {
                                flag = false;
                            }
                            if (flag && arrayElements == countProps) {
                                printShortProperties(beginNumber);
                                counter--;
                            }
                            beginNumber++;
                        }
                    }
                    if (wrongProperties.size() >= 1) {
                        printWrongProperties(wrongProperties);
                        break;
                    }
                }
            }
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
        numberProperties.append(calculator.isJumping(number) ? ", jumping" : "");
        System.out.println(numberProperties);
    }

    void run() {
        printWelcome();
        printUserChoice();
    }
}