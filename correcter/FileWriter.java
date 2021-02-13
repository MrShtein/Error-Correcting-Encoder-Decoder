package correcter;

import java.io.*;

public class FileWriter {

    private File fileAddress;
    private byte[] data;

    public FileWriter(String fileAddress, byte[] data) {
        this.fileAddress = new File(fileAddress);
        this.data = data;
    }

    public void writeData() throws IOException {
        try (BufferedOutputStream buffer = new BufferedOutputStream(new FileOutputStream(fileAddress))) {

            buffer.write(data);

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Something is wrong");
        }
    }

}
