package correcter;

import java.util.ArrayList;

public class HammingCode {


    public byte[] makeHammingCode(byte[] bytes) {
        int mask = 1;
        ArrayList<Byte> bytesList = new ArrayList<>();

        for (byte curByte : bytes) {
            byte firstByte = makeSignificantBits(curByte, 7);
            byte secondByte = makeSignificantBits(curByte, 3);

        }
    }

    private byte makeSignificantBits(byte curByte, int startBitNum) {
        byte temp = 0;
        int mask = 1;
        temp ^= ((curByte & (mask << startBitNum)) >> startBitNum) << 5;
        startBitNum--;

        temp ^= ((curByte & (mask << startBitNum)) >> startBitNum) << 3;
        startBitNum--;

        temp ^= ((curByte & (mask << startBitNum)) >> startBitNum) << 2;
        startBitNum--;

        temp ^= ((curByte & (mask << startBitNum)) >> startBitNum) << 1;

       return temp;
    }

    private byte calcParityBits(byte curByte) {

    }

}
