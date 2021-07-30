package pl.glownia.pamela;

public class Calculator {

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

    boolean isPerfectSquare(double x) {
        double squareRoot = Math.sqrt(x);
        return (squareRoot - Math.floor(squareRoot)) == 0;
    }

    boolean isSunny(long number) {
        return isPerfectSquare(number + 1);
    }

    long takeNumber(String[] array, int index) {
        return Long.parseLong(array[index]);
    }

    String takeProperty(String[] array, int index) {
        return array[index];
    }
}