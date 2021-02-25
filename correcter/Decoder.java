package correcter;

public class Decoder {

    public byte[] decodeBytes(byte[] bytesToDecode) {
        for (int i = 0; i < bytesToDecode.length; i++) {
            byte byteToDecode = bytesToDecode[i];

        }
        return null;
    }

    public void searchAndDecodeByte(byte byteToDecode) {
        int incorrectPairNum = 0;
        try {
            incorrectPairNum = getNumberOfIncorrectPair(byteToDecode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(incorrectPairNum);
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



}
