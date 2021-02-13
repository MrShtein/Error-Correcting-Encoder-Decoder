package correcter;

import java.io.*;

public class FileReader {

    private File fileAddress;

    public FileReader(String fileAddress) {
        this.fileAddress = new File(fileAddress);
    }

    public byte[] readData() throws IOException {
        try (BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(fileAddress))) {

            return buffer.readAllBytes();

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("file not found");
        }
    }

}
