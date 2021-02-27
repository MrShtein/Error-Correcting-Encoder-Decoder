package correcter;

public class TripleBits {

    private byte[] dataToTriple;
    byte tempByte = 0;
    int numOfBit = 7;
    int numOfResultByte = 0;
    int sumOfThreeBits = 0;
    byte[] result;
    int countOfBitMaxThree = 0;

    public TripleBits() {

    }

    public TripleBits(byte[] dataToTriple) {
        this.dataToTriple = dataToTriple;
    }

    public byte[] tripleData() throws Exception {
        int tripleDataLength = amountOfByteInTripledArray();
        return makeMainPartOfByte(dataToTriple, tripleDataLength);
    }

    public byte[] unTripleData(byte[] data) {
        int unTripledDataLength = amountOfByteInUnTripledArray(data);
        return unTripleBytes(data, unTripledDataLength);
    }

    private byte[] unTripleBytes(byte[] data, int length) {
        byte[] result = new byte[length];
        int indexForResult = 0;
        int indexPreparedToAdd = 7;
        byte temp = 0;
        int mask = 1;
        for (int i = 0; i < data.length; i++) {
            byte current = data[i];
            for (int b = 6; b >= 2; b -= 2) {
                if (indexPreparedToAdd < 0) {
                    indexPreparedToAdd = 7;
                    result[indexForResult] = temp;
                    temp = 0;
                    indexForResult++;
                }
                int curBit = ((current & (mask << b)) >> b);
                temp ^= curBit << indexPreparedToAdd;
                indexPreparedToAdd--;
            }
        }
        result[indexForResult] = temp;
        return result;
    }

    private int amountOfByteInUnTripledArray(byte[] data) {
        int currentBitsAmount = data.length * 8;
        int extraBits = data.length * 5;
        int difference = currentBitsAmount - extraBits;
        if  (difference % 8 != 0) {
            return difference / 8 + 1;
        }
        return  (currentBitsAmount - extraBits) / 8;

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
        if (numOfBit > 0) {
            int sum = getSumOfBits(sumOfThreeBits);
            for (int j = 0; j < 2; j++) {
                tempByte ^= sum << j;
            }
            setBitToZero();
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



    public static int getSumOfBits(int sum) throws Exception {
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
        int amountOfTripleBit = dataToTriple.length * bitInByte;
        if (amountOfTripleBit % 3 != 0) {
            return amountOfTripleBit / 3 + 1;
        }
        return amountOfTripleBit / 3;
    }

    public byte[] getDataToTriple() {
        return dataToTriple;
    }
}
