package correcter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String phraseToMix = scan.nextLine();
        SymbolsMixer symbolsMixer = new SymbolsMixer(phraseToMix, ' ', '~');
        String mixedPhrase = symbolsMixer.mixedPhrase();
        System.out.println(mixedPhrase);


    }
}
