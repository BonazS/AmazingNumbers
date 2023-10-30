import java.util.Scanner;

class ConcatenateStringsProblem {

    public static String concatenateStringsWithoutDigits(String[] strings) {
        StringBuilder output = new StringBuilder();
        for (String string : strings) {
            output.append(string);
        }
        for (int i = 0; i < output.length(); i++) {
            if (output.charAt(i) >= 48 && output.charAt(i) <= 57)
                output.deleteCharAt(i--);
        }
        return output.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s+");
        String result = concatenateStringsWithoutDigits(strings);
        System.out.println(result);
    }
}