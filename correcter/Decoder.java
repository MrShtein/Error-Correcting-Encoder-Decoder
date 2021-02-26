package correcter;

public class Decoder {

    public byte[] decodeBytes(byte[] bytesToDecode) {
        byte[] decodedBytes = new byte[bytesToDecode.length];
        for (int i = 0; i < bytesToDecode.length; i++) {
            byte byteToDecode = bytesToDecode[i];
            decodedBytes[i] = searchAndDecodeByte(byteToDecode);
        }
        return decodedBytes;
    }

    public byte searchAndDecodeByte(byte byteToDecode) {
        int incorrectPairNum = 0;
        try {
            incorrectPairNum = getNumberOfIncorrectPair(byteToDecode);
            return decodeByte(byteToDecode, incorrectPairNum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int getNumberOfIncorrectPair(byte researchingByte) throws Exception {
        int numOfPair = 4;
        for (int i = 0; i < 8; i += 2) {
            int firstMask = 1 << i;
            int secMask = 1 << (i + 1);

            int firstBit = researchingByte & firstMask;
            int secBit = (researchingByte & secMask) >> 1;

            if (firstBit != secBit) {
                return numOfPair;
            }
            numOfPair--;
        }
        throw new Exception("Correct byte");
    }

    public byte decodeByte(byte byteToDecode, int numOfIncorrectPair) throws Exception {
        int correctValue;
        switch (numOfIncorrectPair) {
            case 1:
                correctValue = computeCorrectValueFromTwoBitsAndSum(byteToDecode, 4, 2, 0);
                return changeBitsToCorrect(byteToDecode, 7, correctValue);
            case 2:
                correctValue = computeCorrectValueFromTwoBitsAndSum(byteToDecode, 6, 2, 0);
                return changeBitsToCorrect(byteToDecode, 5, correctValue);

            case 3:
                correctValue = computeCorrectValueFromTwoBitsAndSum(byteToDecode, 7, 5, 0);
                return changeBitsToCorrect(byteToDecode, 3, correctValue);
            case 4:
                correctValue = computeCorrectSum(byteToDecode);
                return changeBitsToCorrect(byteToDecode, 1, correctValue);
            default:
                throw new Exception("Something wrong with bits");
        }
    }

    public int computeCorrectValueFromTwoBitsAndSum(byte curByte, int fBit, int sBit, int sumBit) {
        int mask = 1;
        int firstValue = (curByte & (mask << fBit)) >> fBit;
        int secondValue = (curByte & (mask << sBit)) >> sBit;
        int sumValue = (curByte & (mask << sumBit)) >> sumBit;

        int intermediateResult = sumValue - (firstValue + secondValue);

        if (intermediateResult == 0 || intermediateResult == -2) {
            return 0;
        } else {
            return 1;
        }
    }

    public int computeCorrectSum(byte curByte) throws Exception {
        int mask = 1;
        int firstValue = (curByte & (mask << 6)) >> 6;
        int secondValue = (curByte & (mask << 4)) >> 4;
        int thirdValue = (curByte & (mask << 2)) >> 2;

        int sum = firstValue + secondValue + thirdValue;

        return TripleBits.getSumOfBits(sum);
    }

    public byte changeBitsToCorrect(byte curByte, int bitToChange, int correctValue) {
        int mask = 1;
        if (((curByte & (mask << bitToChange)) >> bitToChange) != correctValue) {
            curByte ^= mask << bitToChange;
        }

        if (((curByte & (mask << (bitToChange - 1))) >> (bitToChange - 1)) != correctValue) {
            curByte ^= mask << (bitToChange - 1);
        }

        return curByte;
    }


}
