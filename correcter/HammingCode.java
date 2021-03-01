package correcter;

import java.util.ArrayList;

public class HammingCode {


    public byte[] makeHammingCode(byte[] bytes) {
        int mask = 1;
        byte[] bytesList = new byte[bytes.length * 2];
        int index = 0;
        for (byte curByte : bytes) {
            byte firstSignificantBits = makeSignificantBits(curByte, 7);
            byte secSignificantBits = makeSignificantBits(curByte, 3);

            byte firstByte = calcParityBits(firstSignificantBits);
            byte secondByte = calcParityBits(secSignificantBits);

            bytesList[index] = firstByte;
            index++;

            bytesList[index] = secondByte;
            index++;
        }
        return bytesList;
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
        int firstParityValue = getSumForParity(curByte, 1);
        int secParityValue = getSumForParity(curByte, 2);
        int thirdParityValue = getSumForParity(curByte, 4);

        curByte ^= firstParityValue << 7;
        curByte ^= secParityValue << 6;
        curByte ^= thirdParityValue << 4;

        return curByte;
    }

    private int getSumForParity(byte curByte, int num) {
        int mask = 1;
        switch (num) {
            case 1:
                int firstOne = (curByte & mask << 5) >> 5;
                int secOne = (curByte & mask << 3) >> 3;
                int thirdOne = (curByte & mask << 1) >> 1;
                return (firstOne + secOne + thirdOne) % 2;
            case 2:
                int firstTwo = (curByte & mask << 5) >> 5;
                int secTwo = (curByte & mask << 2) >> 2;
                int thirdTwo = (curByte & mask << 1) >> 1;
                return (firstTwo + secTwo + thirdTwo) % 2;
            case 4:
                int firstThree = (curByte & mask << 3) >> 3;
                int secThree = (curByte & mask << 2) >> 2;
                int thirdThree = (curByte & mask << 1) >> 1;
                return (firstThree + secThree + thirdThree) % 2;
            default:
                throw new IllegalArgumentException("Something wrong with argument");
        }
    }

}
