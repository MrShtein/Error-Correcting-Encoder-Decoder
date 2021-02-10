package correcter;

public class SymbolsMixer {

    private final char beginChar;
    private final char endChar;
    private final char[] symbols;
    private String phraseForMix;

    public SymbolsMixer(String phrase, char beginChar, char endChar) {
        this.beginChar = beginChar;
        this.endChar = endChar;
        symbols = new char[endChar - beginChar + 1];
        setSymbols();
        this.phraseForMix = phrase;
    }

    public void setSymbols() {
        int i = 0;
        for (char start = beginChar; start <= endChar; start++) {
            symbols[i] = start;
            i++;
        }
    }

    public String[] mixSymbolsInPhrase() {
        String[] lettersArray = phraseForMix.split("(?<=\\G...)");
        return lettersArray;
    }

    public String mixedPhrase() {
        String[] mixedPhraseInArray = new String[phraseForMix.length()];
        StringBuilder sb = new StringBuilder();
        for (String item : mixSymbolsInPhrase()) {
            char[] itemToArr = item.toCharArray();

            if (itemToArr.length < 3) {
                sb.append(itemToArr);
                return sb.toString();
            }
            int randomDigitForMix =  (int) (Math.random() * 3);
            int randomDigitForChooseSymbol = (int) (Math.random() * 95);
            itemToArr[randomDigitForMix] = symbols[randomDigitForChooseSymbol];
            sb.append(itemToArr);
        }
    return sb.toString();
    }
}
