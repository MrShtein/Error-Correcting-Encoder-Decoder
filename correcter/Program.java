package correcter;

import java.io.IOException;
import java.util.Arrays;
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
        FileReader fileReader = new FileReader("send.txt");
        HammingCode hammingCode = new HammingCode();

        byte[] stringToEncode = fileReader.readData();
        byte[] encodedData = hammingCode.makeHammingCode(stringToEncode);

        Printer printer = new Printer(stringToEncode);
        System.out.println(printer.sendDataPrint());

        FileWriter fileWriter = new FileWriter("encoded.txt");
        fileWriter.writeData(encodedData);

        System.out.println(printer.encodeModePrint(encodedData));


    }

    public void send() throws IOException {
        FileReader fileReader = new FileReader("encoded.txt");

        ByteMixer byteMixer = new ByteMixer();

        byte[] curBytes = fileReader.readData();
        byte[] mixedBytes = byteMixer.changeBitsInBytes(curBytes);

        FileWriter fileWriter = new FileWriter("received.txt");
        fileWriter.writeData(mixedBytes);

        Printer printer = new Printer();
        String result = printer.sendModePrint(curBytes, mixedBytes);
        System.out.println(result);


    }

    public void decode() throws Exception {
        FileReader fileReader = new FileReader("received.txt");
        FileWriter fileWriter = new FileWriter("decoded.txt");
        HammingCode hammingCode = new HammingCode();
        Printer printer = new Printer();

        byte[] bytesToDecode = fileReader.readData();
        byte[] decodedBytes = hammingCode.decodeHammingCode(bytesToDecode);
        byte[] firstString = hammingCode.makeInitialString(decodedBytes);


        String firstStr = printer.decodeModePrintWithLostByte(bytesToDecode, firstString, decodedBytes);
        String  secStr = "";
        if (firstString[firstString.length - 1] == 0) {
            byte[] temp = Arrays.copyOfRange(firstString, 0, firstString.length - 1);
            fileWriter.writeData(temp);
            secStr = printer.decodeModePrintWithoutLostByte(temp);
        } else {
            secStr = printer.decodeModePrintWithoutLostByte(firstString);
            fileWriter.writeData(firstString);
        }
        System.out.print(firstStr);
        System.out.println(secStr);


    }

}
