package correcter;

import java.util.ArrayList;
import java.util.BitSet;

public class TripleBits {

    private byte[] dataToTriple;


    public TripleBits(byte[] dataToTriple) {
        this.dataToTriple = dataToTriple;
    }

    public void tripleData() throws Exception {
        int tripleDataLength = amountOfByteInTripledArray();
        BitSet bitSet = makeMainPartOfByte(dataToTriple);
        byte[] tripledData = bitSet.toByteArray();
        for (byte item : tripledData) {
            printByte(item);
        }

    }

    private BitSet makeMainPartOfByte(byte[] bytes) throws Exception {
        BitSet bitSet = new BitSet();
        int byteIndexToBitSet = 0;

        for (int b = 0; b < dataToTriple.length; b++) {
            printByte(bytes[b]);
            int countOfBitMaxThree = 1;
            for (int i = 0; i < 8; i++) {
                int mask = 1 << i;
                if (countOfBitMaxThree == 4) {
                    countOfBitMaxThree = 1;
                    int first = bitSet.get(i - 1) ? 1 : 0;
                    int second = bitSet.get(i - 2) ? 1 : 0;
                    int third = bitSet.get(i - 3) ? 1 : 0;;
                    int sum = getSumOfBits(first, second, third);
                    for (int j = 0; j < 2; j++) {
                        bitSet.set(byteIndexToBitSet, sum == 1);
                        byteIndexToBitSet++;
                    }
                } else {
                    for (int j = 0; j < 2; j++) {
                        int value = (mask & bytes[b]) >> i;
                        bitSet.set(byteIndexToBitSet, value == 1);
                        byteIndexToBitSet++;
                    }
                }
                countOfBitMaxThree++;
            }
        }

        System.out.println();
        return bitSet;
    }

    private void printByte(byte byteToPrint) {
        for (int i = 7; i >= 0; i--) {
            int mask = 1 << i;
            int value = (byteToPrint & mask) >> i;
            System.out.print(value);
        }
        System.out.print(" ");
    }

    private int getSumOfBits(int first, int second, int third) throws Exception {
        int sum = first + second + third;
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
