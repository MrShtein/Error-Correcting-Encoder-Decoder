package correcter;

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
        TripleBits tripleBits = new TripleBits(fileReader.readData());

        byte[] dataToTriple = tripleBits.getDataToTriple();
        Printer printer = new Printer(dataToTriple);
        System.out.println(printer.sendDataPrint());

        FileWriter fileWriter = new FileWriter("encoded.txt:", tripleBits.tripleData());
        fileWriter.writeData();
    }

    public void send() {

    }

    public void decode() {

    }

}
