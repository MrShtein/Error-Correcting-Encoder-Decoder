package correcter;

import java.util.ArrayList;
import java.util.BitSet;

public class TripleBits {

    private byte[] dataToTriple;
    byte tempByte = 0;
    int numOfBit = 7;
    int numOfResultByte = 0;
    int sumOfThreeBits = 0;
    byte[] result;
    int countOfBitMaxThree = 0;


    public TripleBits(byte[] dataToTriple) {
        this.dataToTriple = dataToTriple;
    }

    public void tripleData() throws Exception {
        int tripleDataLength = amountOfByteInTripledArray();
        byte[] tripledData = makeMainPartOfByte(dataToTriple, tripleDataLength);
        for (byte item : tripledData) {
            printByte(item);
        }

    }

    private byte[] makeMainPartOfByte(byte[] bytes, int tripledDataLength) throws Exception {
        result = new byte[tripledDataLength];
        for (int b = 0; b < dataToTriple.length; b++) {
            for (int i = 7; i >= 0; i--) {
                int mask = 1 << i;
                if (countOfBitMaxThree == 3) {
                    countOfBitMaxThree = 0;
                    int sum = getSumOfBits(sumOfThreeBits);
                    for (int j = 0; j < 2; j++) {
                        tempByte ^= sum << numOfBit;
                        numOfBit--;
                        if (numOfBit < 0) setBitToZero();
                    }
                    sumOfThreeBits = 0;
                }
                setTwoBitsWithoutSum(mask, bytes, b, i);
                countOfBitMaxThree++;
            }
        }
        return result;
    }

    private void setTwoBitsWithoutSum(int mask, byte[] bytes, int b, int i) {
        int value = (mask & bytes[b]) >> i;
        sumOfThreeBits += value;
        for (int j = 0; j < 2; j++) {
            tempByte ^= (value << numOfBit);
            numOfBit--;
            if (numOfBit < 0) setBitToZero();
        }
    }

    private void setBitToZero() {
        result[numOfResultByte] = tempByte;
        tempByte = 0;
        numOfBit = 7;
        numOfResultByte++;
    }

    private void printByte(byte byteToPrint) {
        for (int i = 7; i >= 0; i--) {
            int mask = 1 << i;
            int value = (byteToPrint & mask) >> i;
            System.out.print(value);
        }
        System.out.println();
    }

    private int getSumOfBits(int sum) throws Exception {
        switch (sum) {
            case 0:
            case 2:
                return 0;
            case 1:
            case 3:
                return 1;
            default:
                throw new Exception("Something wrong with bits");
        }
    }

    private int amountOfByteInTripledArray() {
        int bitInByte = 8;
        int amountOfTripleBit = dataToTriple.length * bitInByte * 2;
        int extraBits = amountOfTripleBit / 3 + amountOfTripleBit % 3;
        int allBits = amountOfTripleBit + extraBits;
        int countOfBytes = allBits / 8;
        if (allBits % 8 != 0) {
            countOfBytes++;
        }
        return countOfBytes;

    }

    public byte[] getDataToTriple() {
        return dataToTriple;
    }
}
