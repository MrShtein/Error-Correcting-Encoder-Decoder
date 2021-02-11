package correcter;

public class SymbolsUnmixer {

    private String stringForUnmix;

    public SymbolsUnmixer(String stringForUnmix) {
        this.stringForUnmix = stringForUnmix;
    }

    public String unmixString() {
        StringBuilder sb = new StringBuilder();
        String[] separatedString = separateString();

        for (String letters : separatedString) {
            sb.append(getLetterFromThreeSymbols(letters));
        }

        return sb.toString();
    }

    public String[] separateString() {
        return stringForUnmix.split("(?<=\\G...)");
    }

    public char getLetterFromThreeSymbols(String threeLetters) {
        if (threeLetters.charAt(0) == threeLetters.charAt(1) || threeLetters.charAt(0) == threeLetters.charAt(2)) {
            return threeLetters.charAt(0);
        }
        return threeLetters.charAt(1);
    }


}
