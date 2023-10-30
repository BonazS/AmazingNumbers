public class Main {
    public static void main(String[] args) {
        anotherMethod();
        System.out.println(number); // Ora "number" è accessibile dal metodo "main"
    }

    public static void anotherMethod() {
        static int number = 42; // Variabile "number" è ora pubblica e accessibile ovunque all'interno della classe.
    }
}