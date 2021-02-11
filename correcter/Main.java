package correcter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String phraseToMix = scan.nextLine();
        System.out.println(phraseToMix);

        SymbolsTriple symbolsTriple = new SymbolsTriple(phraseToMix);
        SymbolsMixer symbolsMixer = new SymbolsMixer(symbolsTriple.tripleLetters(), ' ', '~');
        String mixedPhrase = symbolsMixer.mixedPhrase();
        SymbolsUnmixer symbolsUnmixer = new SymbolsUnmixer(mixedPhrase);
        String unmixedPhrase = symbolsUnmixer.unmixString();
        System.out.println(unmixedPhrase);


    }
}
