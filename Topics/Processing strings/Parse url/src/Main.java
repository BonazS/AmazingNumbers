import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();

        String[] urlParts = url.split("\\?");
        String[] urlParameters = urlParts[1].split("&");
        String password = null;
        for (String parameter : urlParameters) {
            String[] keysValues = parameter.split("=");
            if (keysValues[0].equals("pass") && keysValues.length == 2) {
                password = keysValues[1];
            }
            System.out.println(keysValues[0] + " : " + (keysValues.length == 2 ? keysValues[1] : "not found"));
        }
        if (password != null) System.out.println("password : " + password);
    }
}