import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        String[] characters = input.split("");
        for (int i = 0; i < characters.length; i++) {
            System.out.print(characters[i].concat(characters[i]));
        }
    }
}