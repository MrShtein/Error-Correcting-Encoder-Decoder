package correcter;

import java.io.*;

public class FileWriter {

    private File fileAddress;

    public FileWriter(String fileAddress) {
        this.fileAddress = new File(fileAddress);
    }

    public void writeData(byte[] data) throws IOException {
        try (BufferedOutputStream buffer = new BufferedOutputStream(new FileOutputStream(fileAddress))) {

            buffer.write(data);

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Something is wrong");
        }
    }

}
