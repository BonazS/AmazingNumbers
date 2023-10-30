class EnglishAlphabet {

    public static StringBuilder createEnglishAlphabet() {
        StringBuilder engAlphabet = new StringBuilder();
        char letter;
        for (letter = 'A'; letter != 'Z'; letter++) {
            engAlphabet.append(letter).append(" ");
        }
        return engAlphabet.append(letter);
    }
}