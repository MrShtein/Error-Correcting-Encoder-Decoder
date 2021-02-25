package correcter;

import java.io.IOException;
import java.util.Scanner;

public class Program {

    private final Scanner scan;
    private boolean isWork;

    public Program() {
        scan = new Scanner(System.in);
        isWork = true;
    }

    public void run() throws Exception {
        while (isWork) {
            String action;
            try {
                System.out.print("Write a mode: ");
                action = scan.nextLine();
            } catch (Exception e) {
                throw new Exception("You should enter actually query");
            }

            switch (action) {
                case "encode":
                    encode();
                    isWork = false;
                    break;
                case "send":
                    send();
                    isWork = false;
                    break;
                case "decode":
                    decode();
                    isWork = false;
                    break;
            }
        }
    }

    public void encode() throws Exception {
        FileReader fileReader = new FileReader("/home/mrshtein/IdeaProjects/Correcting-Encoder-Decoder/Error Correcting Encoder-Decoder/task/src/correcter/send.txt");
        TripleBits tripleBits = new TripleBits(fileReader.readData());

        byte[] dataToTriple = tripleBits.getDataToTriple();
        byte[] tripledData = tripleBits.tripleData();

        Printer printer = new Printer(dataToTriple);
        System.out.println(printer.sendDataPrint());

        FileWriter fileWriter = new FileWriter("/home/mrshtein/IdeaProjects/Correcting-Encoder-Decoder/Error Correcting Encoder-Decoder/task/src/correcter/encoded.txt", tripledData);
        fileWriter.writeData();

        System.out.println(printer.encodeModePrint(tripledData));


    }

    public void send() throws IOException {
        FileReader fileReader = new FileReader("/home/mrshtein/IdeaProjects/Correcting-Encoder-Decoder/Error Correcting Encoder-Decoder/task/src/correcter/encoded.txt");

        ByteMixer byteMixer = new ByteMixer();

        byte[] curBytes = fileReader.readData();
        byte[] mixedBytes = byteMixer.changeBitsInBytes(curBytes);

        FileWriter fileWriter = new FileWriter("/home/mrshtein/IdeaProjects/Correcting-Encoder-Decoder/Error Correcting Encoder-Decoder/task/src/correcter/received.txt", mixedBytes);
        fileWriter.writeData();

        Printer printer = new Printer();
        String result = printer.sendModePrint(curBytes, mixedBytes);
        System.out.println(result);


    }

    public void decode() throws IOException {
        FileReader fileReader = new FileReader("/home/mrshtein/IdeaProjects/Correcting-Encoder-Decoder/Error Correcting Encoder-Decoder/task/src/correcter/received.txt");
        byte[] bytesToDecode = fileReader.readData();

    }

}
