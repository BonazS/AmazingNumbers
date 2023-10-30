package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();

        String firstParameter = "";
        long firstNumber = -1, secondNumber;
        String[] properties;

        do {
            if (firstParameter == "") {
                printRequests();
            }
            System.out.print("Enter a request: ");
            Scanner scanner = new Scanner(System.in);
            String[] parameters = scanner.nextLine().split(" ");
            System.out.println();
            if (parameters[0] != "") {
                try {
                    firstParameter = parameters[0];
                    firstNumber = Long.parseLong(firstParameter);
                    if (parameters.length == 1) {
                        if (firstNumber < 0) {
                            System.out.println("The first parameter should be a natural number or zero.");
                        } else if (firstNumber >= 1) {
                            System.out.println("Properties of " + firstNumber);
                            System.out.println("even: " + checkParity(firstNumber));
                            System.out.println("odd: " + !checkParity(firstNumber));
                            System.out.println("buzz: " + checkBuzz(firstNumber));
                            System.out.println("duck: " + checkDuck(firstNumber));
                            System.out.println("palindromic: " + checkPalindromic(firstNumber));
                            System.out.println("gapful: " + checkGapful(firstNumber));
                            System.out.println("spy: " + checkSpy(firstNumber));
                            System.out.println("square: " + checkSquare(firstNumber));
                            System.out.println("sunny: " + checkSunny(firstNumber));
                            System.out.println("jumping: " + checkJumping(firstNumber));
                            System.out.println("happy: " + checkHappy(firstNumber));
                            System.out.println("sad: " + !checkHappy(firstNumber));
                        } else {
                            System.out.println("Goodbye!");
                        }
                    } else if (parameters.length == 2) {
                        secondNumber = Long.parseLong(parameters[1]);
                        if (firstNumber < 0) {
                            System.out.println("The first parameter should be a natural number or zero.");
                        } else if (secondNumber <= 0) {
                            System.out.println("The second parameter should be a natural number.");
                        } else {
                            for (int i = 0; i < secondNumber; i++, firstNumber++) {
                                printNumberProperties(firstNumber);
                            }
                        }
                    } else if (parameters.length == 3) {
                        secondNumber = Long.parseLong(parameters[1]);
                        properties = new String[] {parameters[2]};
                        if (existsProperties(properties)) {
                            printConsecutiveNumbersProperties(firstNumber, secondNumber, properties);
                        }
                    } else if (parameters.length >= 4) {
                        secondNumber = Long.parseLong(parameters[1]);
                        properties = new String[parameters.length - 2];
                        System.arraycopy(parameters, 2, properties, 0, parameters.length - 2);
                        if (existsProperties(properties)) {
                            if (!existsMutuallyExclusiveProperties(properties)) {
                                printConsecutiveNumbersProperties(firstNumber, secondNumber, properties);
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
                System.out.println();
            } else {
                firstParameter = "";
                firstNumber = -1;
            }
        } while (firstNumber != 0);
    }

    static void printRequests() {
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("* the first parameter represents a starting number;");
        System.out.println("* the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
    }

    static boolean existsProperties(String[] properties) {
        boolean result = true;
        int unExistsProperties = 0;
        final StringBuilder unExistMessage = new StringBuilder();

        for (String property : properties) {
            if (property.startsWith("-")) property = property.substring(1);
            if (!(property.equalsIgnoreCase("BUZZ")
                    || property.equalsIgnoreCase("DUCK")
                    || property.equalsIgnoreCase("PALINDROMIC")
                    || property.equalsIgnoreCase("GAPFUL")
                    || property.equalsIgnoreCase("SPY")
                    || property.equalsIgnoreCase("SQUARE")
                    || property.equalsIgnoreCase("SUNNY")
                    || property.equalsIgnoreCase("JUMPING")
                    || property.equalsIgnoreCase("HAPPY")
                    || property.equalsIgnoreCase("SAD")
                    || property.equalsIgnoreCase("EVEN")
                    || property.equalsIgnoreCase("ODD"))) {
                unExistMessage.append(property.toUpperCase()).append(", ");
                result = false;
                unExistsProperties++;
            }
        }
        if (!result) {
            unExistMessage.delete(unExistMessage.length() - 2, unExistMessage.length());

            if (unExistsProperties > 1) {
                unExistMessage.insert(0, "The properties [");
                unExistMessage.append("] are wrong.");
            } else {
                unExistMessage.insert(0, "The property [");
                unExistMessage.append("] is wrong.");
            }
            System.out.println(unExistMessage);
            System.out.println("Available properties: " +
                    "[BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD, EVEN, ODD]");
        }
        return result;
    }

    static boolean existsMutuallyExclusiveProperties(String[] properties) {
        boolean isExcludeCurrentProperty = false, isExcludeScanProperty = false;
        for (int i = 0; i < properties.length - 1; i++) {
            if (properties[i].startsWith("-")) isExcludeCurrentProperty = true;
            String currentProperty = isExcludeCurrentProperty ? properties[i].substring(1) : properties[i];
            for (int j = i + 1; j < properties.length; j++) {
                switch (currentProperty.toUpperCase()) {
                    case "DUCK":
                        if (
                                (!isExcludeCurrentProperty &&
                                        (properties[j].equalsIgnoreCase("SPY")
                                                || String.format("-%s", properties[j]
                                                                        .substring(1)
                                                                        .toUpperCase()
                                                ).equals("-DUCK")
                                        )
                                ) || (isExcludeCurrentProperty &&
                                        (properties[j].equalsIgnoreCase("DUCK")
                                                || String.format("-%s", properties[j]
                                                                        .substring(1)
                                                                        .toUpperCase()
                                                ).equals("-SPY")
                                        )
                                )
                        ) {
                            printMutuallyExclusiveProperties(properties[i], properties[j]);
                            return true;
                        }
                        break;
                    case "SPY":
                        if (
                                (!isExcludeCurrentProperty &&
                                        (properties[j].equalsIgnoreCase("DUCK")
                                                || String.format("-%s", properties[j]
                                                                        .substring(1)
                                                                        .toUpperCase()
                                                ).equals("-SPY")
                                        )
                                ) || (isExcludeCurrentProperty &&
                                        (properties[j].equalsIgnoreCase("SPY")
                                                || String.format("-%s", properties[j]
                                                                        .substring(1)
                                                                        .toUpperCase()
                                                ).equals("-DUCK")
                                        )
                                )
                        ) {
                            printMutuallyExclusiveProperties(properties[i], properties[j]);
                            return true;
                        }
                        break;
                    case "SQUARE":
                        if (
                                (!isExcludeCurrentProperty &&
                                        (properties[j].equalsIgnoreCase("SUNNY")
                                                || String.format("-%s", properties[j]
                                                                        .substring(1)
                                                                        .toUpperCase()
                                                ).equals("-SQUARE")
                                        )
                                ) || (isExcludeCurrentProperty &&
                                        (properties[j].equalsIgnoreCase("SQUARE")
                                                || String.format("-%s", properties[j]
                                                                        .substring(1)
                                                                        .toUpperCase()
                                                ).equals("-SUNNY")
                                        )
                                )
                        ) {
                            printMutuallyExclusiveProperties(properties[i], properties[j]);
                            return true;
                        }
                        break;
                    case "SUNNY":
                        if (
                                (!isExcludeCurrentProperty &&
                                        (properties[j].equalsIgnoreCase("SQUARE")
                                                || String.format("-%s", properties[j]
                                                                        .substring(1)
                                                                        .toUpperCase()
                                                ).equals("-SUNNY")
                                        )
                                ) || (isExcludeCurrentProperty &&
                                        (properties[j].equalsIgnoreCase("SUNNY")
                                                || String.format("-%s", properties[j]
                                                                        .substring(1)
                                                                        .toUpperCase()
                                                ).equals("-SQUARE")
                                        )
                                )
                        ) {
                            printMutuallyExclusiveProperties(properties[i], properties[j]);
                            return true;
                        }
                        break;
                    case "HAPPY":
                        if (
                                (!isExcludeCurrentProperty &&
                                        (properties[j].equalsIgnoreCase("SAD")
                                                || String.format("-%s", properties[j]
                                                                        .substring(1)
                                                                        .toUpperCase()
                                                ).equals("-HAPPY")
                                        )
                                ) || (isExcludeCurrentProperty &&
                                        (properties[j].equalsIgnoreCase("HAPPY")
                                                || String.format("-%s", properties[j]
                                                                        .substring(1)
                                                                        .toUpperCase()
                                                ).equals("-SAD")
                                        )
                                )
                        ) {
                            printMutuallyExclusiveProperties(properties[i], properties[j]);
                            return true;
                        }
                        break;
                    case "SAD":
                        if (
                                (!isExcludeCurrentProperty &&
                                        (properties[j].equalsIgnoreCase("HAPPY")
                                                || String.format("-%s", properties[j]
                                                                        .substring(1)
                                                                        .toUpperCase()
                                                ).equals("-SAD")
                                        )
                                ) || (isExcludeCurrentProperty &&
                                        (properties[j].equalsIgnoreCase("SAD")
                                                || String.format("-%s", properties[j]
                                                                        .substring(1)
                                                                        .toUpperCase()
                                                ).equals("-HAPPY")
                                        )
                                )
                        ) {
                            printMutuallyExclusiveProperties(properties[i], properties[j]);
                            return true;
                        }
                        break;
                    case "EVEN":
                        if (
                                (!isExcludeCurrentProperty &&
                                        (properties[j].equalsIgnoreCase("ODD")
                                                || String.format("-%s", properties[j]
                                                                        .substring(1)
                                                                        .toUpperCase()
                                                ).equals("-EVEN")
                                        )
                                ) || (isExcludeCurrentProperty &&
                                        (properties[j].equalsIgnoreCase("EVEN")
                                                || String.format("-%s", properties[j]
                                                                        .substring(1)
                                                                        .toUpperCase()
                                                ).equals("-ODD")
                                        )
                                )
                        ) {
                            printMutuallyExclusiveProperties(properties[i], properties[j]);
                            return true;
                        }
                        break;
                    case "ODD":
                        if (
                                (!isExcludeCurrentProperty &&
                                        (properties[j].equalsIgnoreCase("EVEN")
                                                || String.format("-%s", properties[j]
                                                                        .substring(1)
                                                                        .toUpperCase()
                                                ).equals("-ODD")
                                        )
                                ) || (isExcludeCurrentProperty &&
                                        (properties[j].equalsIgnoreCase("ODD")
                                                || String.format("-%s", properties[j]
                                                                        .substring(1)
                                                                        .toUpperCase()
                                                ).equals("-EVEN")
                                        )
                                )
                        ) {
                            printMutuallyExclusiveProperties(properties[i], properties[j]);
                            return true;
                        }
                }
            }
        }
        return false;
    }

    static boolean checkParity(long number) {
        return number % 2 == 0;
    }

    static boolean checkBuzz(long number) {
        boolean isEndsSeven = number % 10 == 7;
        long divisibleSevenNumber = (number / 10) - (number % 10 * 2);
        boolean isDivisibleSeven = divisibleSevenNumber % 7 == 0;
        return isEndsSeven || isDivisibleSeven;
    }

    static boolean checkDuck(long number) {
        for (; number / 10 > 0; number /= 10) {
            if (number % 10 == 0) return true;
        }
        return number == 0;
    }

    static boolean checkPalindromic(long number) {
        final StringBuilder palindromicNumber = new StringBuilder(String.valueOf(number));
        return palindromicNumber.toString().contentEquals(palindromicNumber.reverse());
    }

    static boolean checkGapful(long number) {
        if (number < 100) return false;

        String stringNumber = String.valueOf(number);
        String firstAndLast = stringNumber.charAt(0) + "" + stringNumber.charAt(stringNumber.length() - 1);
        long divisor = Long.parseLong(firstAndLast);

        return number % divisor == 0;
    }

    static boolean checkSpy(long number) {
        if (number / 10 == 0) return true;
        long sum, prod;
        for (sum = 0, prod = 1; number / 10 > 0; number /= 10) {
            sum += number % 10;
            prod *= number % 10;
        }
        return sum + (number % 10) == prod * (number % 10);
    }

    static boolean checkSquare(long number) {
        return Math.sqrt(number) == (long) Math.sqrt(number);
    }

    static boolean checkSunny(long number) {
        return checkSquare(number + 1);
    }

    static boolean checkJumping(long number) {
        final String digits = String.valueOf(number);
        for (int i = 0; i < digits.length() - 1; i++) {
            final int diffConsecutiveNumbers = digits.charAt(i + 1) - digits.charAt(i);
            if (digits.charAt(i) == '0' && digits.charAt(i + 1) != '1') {
                return false;
            } else if (digits.charAt(i) == '9' && digits.charAt(i + 1) != '8') {
                return false;
            } else if (diffConsecutiveNumbers != 1 && diffConsecutiveNumbers != -1) {
                return false;
            }
        }
        return true;
    }

    static boolean checkHappy(final long number) {
        long slow = number, fast = number;

        do {
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
        } while (slow != fast);

        return slow == 1;
    }

    private static long squareSum(long n) {
        long sum = 0;
        while (n > 0) {
            long digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    static void printMutuallyExclusiveProperties(String current, String next) {
        final StringBuilder mutuallyExclusiveMessage = new StringBuilder();
        mutuallyExclusiveMessage
                .append("The request contains mutually exclusive properties: [")
                .append((current.startsWith("-") ? "-" : ""))
                .append((current.startsWith("-") ? current.substring(1) : current).toUpperCase())
                .append(", ")
                .append((next.startsWith("-") ? "-" : ""))
                .append((next.startsWith("-") ? next.substring(1) : next).toUpperCase())
                .append("]");
        System.out.println(mutuallyExclusiveMessage);
        System.out.println("There are no numbers with these properties.");
    }

    static void printNumberProperties(long number) {
        System.out.print(number + " is ");
        if (checkBuzz(number)) {
            System.out.print("buzz, ");
        }
        if (checkDuck(number)) {
            System.out.print("duck, ");
        }
        if (checkPalindromic(number)) {
            System.out.print("palindromic, ");
        }
        if (checkGapful(number)) {
            System.out.print("gapful, ");
        }
        if (checkSpy(number)) {
            System.out.print("spy, ");
        }
        if (checkSquare(number)) {
            System.out.print("square, ");
        }
        if (checkSunny(number)) {
            System.out.print("sunny, ");
        }
        if (checkJumping(number)) {
            System.out.print("jumping, ");
        }
        if (checkHappy(number)) {
            System.out.print("happy, ");
        } else {
            System.out.print("sad, ");
        }
        if (checkParity(number)) {
            System.out.println("even");
        } else {
            System.out.println("odd");
        }
    }

    static void printConsecutiveNumbersProperties(long startNumber, long nNumbers, String[] properties) {
        for (int i = 0; i < nNumbers; startNumber++) {
            String[] alreadyCheckedProperties = new String[properties.length];
            int lastIndexFilled = 0;
            boolean isExcludeProperty = false, matchAllProperties = false;
            for (String property : properties) {
                if (property.startsWith("-")) {
                    property = property.substring(1);
                    isExcludeProperty = true;
                } else {
                    isExcludeProperty = false;
                }
                boolean isCheckedProperty = false;
                for (int j = 0; j < lastIndexFilled; j++) {
                    if (alreadyCheckedProperties[j].equals(property.toUpperCase())) {
                        isCheckedProperty = true;
                        break;
                    }
                }
                if (!isCheckedProperty) {
                    switch (property.toUpperCase()) {
                        case "BUZZ":
                            matchAllProperties = isExcludeProperty != checkBuzz(startNumber);
                            break;
                        case "DUCK":
                            matchAllProperties = isExcludeProperty != checkDuck(startNumber);
                            break;
                        case "PALINDROMIC":
                            matchAllProperties = isExcludeProperty != checkPalindromic(startNumber);
                            break;
                        case "GAPFUL":
                            matchAllProperties = isExcludeProperty != checkGapful(startNumber);
                            break;
                        case "SPY":
                            matchAllProperties = isExcludeProperty != checkSpy(startNumber);
                            break;
                        case "SQUARE":
                            matchAllProperties = isExcludeProperty != checkSquare(startNumber);
                            break;
                        case "SUNNY":
                            matchAllProperties = isExcludeProperty != checkSunny(startNumber);
                            break;
                        case "JUMPING":
                            matchAllProperties = isExcludeProperty != checkJumping(startNumber);
                            break;
                        case "HAPPY":
                            matchAllProperties = isExcludeProperty != checkHappy(startNumber);
                            break;
                        case "SAD":
                            matchAllProperties = isExcludeProperty == checkHappy(startNumber);
                            break;
                        case "EVEN":
                            matchAllProperties = isExcludeProperty != checkParity(startNumber);
                            break;
                        case "ODD":
                            matchAllProperties = isExcludeProperty == checkParity(startNumber);
                            break;
                        default:
                            return;
                    }
                    alreadyCheckedProperties[lastIndexFilled++] = property.toUpperCase();
                }
                if (!matchAllProperties) break;
            }
            if (matchAllProperties) {
                printNumberProperties(startNumber);
                i++;
            }
        }
    }
}