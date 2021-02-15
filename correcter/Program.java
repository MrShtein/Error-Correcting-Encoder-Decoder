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

    public void encode() throws IOException {
        FileReader fileReader = new FileReader("send.txt");

    }

    public void send() {

    }

    public void decode() {

    }

}
