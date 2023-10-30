class Util {
    public static String capitalize(String str) {
        System.out.println("Before: " + str);
        System.out.print("After: ");
        if (str == null || str.isBlank()) {
            System.out.print(str);
            return str;
        }

        if (str.length() == 1) {
            System.out.print(str.toUpperCase());
            return str.toUpperCase();
        }

        System.out.print(
                Character.toUpperCase(str.charAt(0)) +
                        str.substring(1)
        );
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}