package correcter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader("send.txt");

        ByteMixer byteMixer = new ByteMixer(fileReader.readData(), 2, 5);
        FileWriter fileWriter = new FileWriter("received.txt", byteMixer.mixBitInByteArray());
        fileWriter.writeData();


    }
}
