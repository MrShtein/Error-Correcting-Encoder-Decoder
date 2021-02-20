package correcter;

import java.sql.Struct;

public class Printer {

    private final String SEND_FILENAME = "send.txt";
    private final String ENCODED_FILENAME = "encoded.txt";
    private final String RECEIVED_FILENAME = "received.txt";
    private final String DECODED_FILENAME = "decoded.txt";

    private final byte[] clearData;
    StringBuilder sb;

    public Printer(byte[] clearData) {
        this.clearData = clearData;
        sb = new StringBuilder();
    }

    protected String sendDataPrint() {
        sb.append(SEND_FILENAME)
                .append("\n")
                .append(String.format("text view: %s\n", new String(clearData)))
                .append("hex view:")
                .append(printBytesHexView(clearData))
                .append("bin view:")
                .append(byteArrayPrint(clearData));
        return sb.toString();
    }

    private String byteArrayPrint(byte[] bytesToPrint) {
        StringBuilder sb = new StringBuilder();
        for (byte oneByte : bytesToPrint) {
            sb.append(" ").append(printByteBinView(oneByte));
        }
        return sb.toString();
    }

    private String printBytesHexView(byte[] bytesToPrint) {
        StringBuilder sb = new StringBuilder();
        for (byte oneByte : bytesToPrint) {
            sb.append(String.format(" %s", Integer.toHexString(oneByte)));
        }
        sb.append("\n");
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
