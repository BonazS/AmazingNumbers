import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        int nShift = scanner.nextInt();
        if (nShift > string.length()) {
            System.out.println(string);
        } else {
            char[] lastPart = new char[nShift];
            char[] firstPart = new char[string.length() - nShift];
            for (int i = 0; i < lastPart.length; i++) {
                lastPart[i] = string.charAt(i);
            }
            for (int i = 0; i < firstPart.length; i++) {
                firstPart[i] = string.charAt(nShift + i);
            }
            System.out.println(String.valueOf(firstPart).concat(String.valueOf(lastPart)));
        }
    }
}