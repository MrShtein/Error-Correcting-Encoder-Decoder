package correcter;

public class Printer {

    private final String SEND_FILENAME = "send.txt";
    private final String ENCODED_FILENAME = "encoded.txt";
    private final String RECEIVED_FILENAME = "received.txt";
    private final String DECODED_FILENAME = "decoded.txt";

    private  byte[] clearData;

    public Printer() {

    }

    public Printer(byte[] clearData) {
        this.clearData = clearData;
    }

    protected String sendModePrint(byte[] encodeData, byte[] receivedData) {
        StringBuilder sb = new StringBuilder("\n");
        sb.append(ENCODED_FILENAME)
                .append(":")
                .append("\nhex view:")
                .append(printBytesHexView(encodeData))
                .append("\nbin view:")
                .append(byteArrayPrint(encodeData))
                .append("\n\n")
                .append(RECEIVED_FILENAME)
                .append(":")
                .append("\nbin view:")
                .append(byteArrayPrint(receivedData))
                .append("\nhex view:")
                .append(printBytesHexView(receivedData));

        return sb.toString();
    }

    protected String encodeModePrint(byte[] bytesToPrint) {
        StringBuilder sb = new StringBuilder();
        sb.append(ENCODED_FILENAME)
                .append(":")
                .append("\nexpand:")
                .append(byteArrayPrintParity(bytesToPrint))
                .append("\nparity:")
                .append(byteArrayPrint(bytesToPrint))
                .append("\nhex view:")
                .append(printBytesHexView(bytesToPrint));
        return sb.toString();

    }

    protected String sendDataPrint() {
        StringBuilder sb = new StringBuilder();
        sb.append(SEND_FILENAME)
                .append("\n")
                .append(String.format("text view: %s", new String(clearData)))
                .append("\nhex view:")
                .append(printBytesHexView(clearData))
                .append("\nbin view:")
                .append(byteArrayPrint(clearData));
        return sb.toString();
    }

    public String byteArrayPrintParity(byte[] bytesToPrint) {
        StringBuilder sb = new StringBuilder();
        for (byte oneByte : bytesToPrint) {
            sb.append(" ").append(printByteBinView(oneByte));
            sb.replace(sb.length() - 2, sb.length(), "..");
        }
        System.out.println();
        return sb.toString();
    }

    public String byteArrayPrint(byte[] bytesToPrint) {
        StringBuilder sb = new StringBuilder();
        for (byte oneByte : bytesToPrint) {
            sb.append(" ").append(printByteBinView(oneByte));
        }
        return sb.toString();
    }

    private String printBytesHexView(byte[] bytesToPrint) {
        StringBuilder sb = new StringBuilder();
        for (byte oneByte : bytesToPrint) {
            String hex = Integer.toHexString(0xFF & oneByte);
            sb.append(" ")
                    .append(hex.toUpperCase());
            if (hex.length() == 1) {
                sb.append(0);
            }

        }
        return sb.toString();
    }

    private String printByteBinView(byte byteToPrint) {
        StringBuilder sb = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            int mask = 1 << i;
            int value = (byteToPrint & mask) >> i;
            sb.append(value);
        }
        return sb.toString();
    }


}
