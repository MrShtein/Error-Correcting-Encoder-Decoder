package correcter;

import java.util.ArrayList;

public class HammingCode {


    public byte[] decodeHammingCode(byte[] bytes) {
        int mask = 1;
        byte[] decodedBytes = new byte[bytes.length];
        int numOfByte;
        for (int i = 0; i < bytes.length; i++) {
            byte firstByte = bytes[i];
            int numForFirstBit = computeNumberOfIncorrectBit(firstByte);
            switch (numForFirstBit) {
                case 1:
                    numOfByte = 7;
                    break;
                case 2:
                    numOfByte = 6;
                    break;
                case 3:
                    numOfByte = 5;
                    break;
                case 4:
                    numOfByte = 4;
                    break;
                case 5:
                    numOfByte = 3;
                    break;
                case 6:
                    numOfByte = 2;
                    break;
                case 7:
                    numOfByte = 1;
                    break;
                default:
                    throw new IllegalArgumentException("Something wrong");
            }
            firstByte ^= mask << numOfByte;
            decodedBytes[i] = firstByte;
        }
        return decodedBytes;
    }

    public byte[] makeInitialString(byte[] curBytes) {
        byte[] decodedBytes = new byte[curBytes.length / 2];
        int index = 0;
        for (int i = 0; i < curBytes.length; i = i + 2) {
            decodedBytes[index] = makeOneByteFromTwoByte(curBytes[i], curBytes[i + 1]);
            index++;
        }
        return decodedBytes;
    }

    private byte makeOneByteFromTwoByte(byte first, byte second) {
        int mask = 1;
        byte correct = 0;
        correct ^= ((first & mask << 5) >> 5) << 7;
        correct ^= ((first & mask << 3) >> 3) << 6;
        correct ^= ((first & mask << 2) >> 2) << 5;
        correct ^= ((first & mask << 1) >> 1) << 4;
        correct ^= ((second & mask << 5) >> 5) << 3;
        correct ^= ((second & mask << 3) >> 3) << 2;
        correct ^= ((second & mask << 2) >> 2) << 1;
        correct ^= ((second & mask << 1) >> 1);
        return correct;
    }

    private int computeNumberOfIncorrectBit(byte curByte) {
        int mask = 1;
        int numOfIncorrectBit = 0;
        int firstBit = getSumForParity(curByte, 1);
        int secBit = getSumForParity(curByte, 2);
        int thirdBit = getSumForParity(curByte, 4);

        boolean first = firstBit == (curByte & mask << 7) >> 7;
        boolean sec = secBit == (curByte & mask << 6) >> 6;
        boolean third = thirdBit == (curByte & mask << 4) >> 4;
        if (!first) {
            numOfIncorrectBit += 1;
        }
        if (!sec) {
            numOfIncorrectBit += 2;
        }
        if (!third) {
            numOfIncorrectBit += 4;
        }
        return numOfIncorrectBit;
    }

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
