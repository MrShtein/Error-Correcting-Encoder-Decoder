package correcter;

public class SymbolsTriple {

    private String messageToTriple;

    public SymbolsTriple(String messageToTriple) {
        this.messageToTriple = messageToTriple;
    }

    public String tripleLetters() {
        char[] messageInArray = messageToTriple.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (char symbol : messageInArray) {
            for (int i = 0; i < 3; i++) {
                sb.append(symbol);
            }
        }

        return sb.toString();
    }

}
