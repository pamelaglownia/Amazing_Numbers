package pl.glownia.pamela;

import java.util.Scanner;

public class BuzzNumber {

    void checkIfIsEvenOrOdd(int number) {
        if (number % 2 == 0) {
            System.out.println("This number is even.");
        } else {
            System.out.println("This number is odd.");
        }
    }

    void checkIfIsBuzzNumber(int number) {
        if (number % 7 == 0 || number % 10 == 7) {
            System.out.println("It is a Buzz number.");
            System.out.println("Explanation:");
            if (number % 7 == 0 && number % 10 == 7) {
                System.out.println(number + " is divisible by 7 and ends with 7.");
            } else if (number % 7 == 0) {
                System.out.println(number + " is divisible by 7.");
            } else if (number % 10 == 7) {
                System.out.println(number + " ends with 7.");
            }
        } else {
            System.out.println("It is not a Buzz number.");
            System.out.println("Explanation:");
            System.out.println(number + " is neither divisible by 7 nor does it end with 7.");
        }
    }

    void checkUserNumber() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        int number = scan.nextInt();
        if (number <= 0) {
            System.out.println("This number is not natural!");
        } else {
            checkIfIsEvenOrOdd(number);
            checkIfIsBuzzNumber(number);
        }
    }
}