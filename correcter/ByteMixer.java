package correcter;

public class ByteMixer {

    private  byte[] phraseInByte;
    private  final int MIN_BIT_TO_CHANGE;
    private  final int MAX_BIT_TO_CHANGE;

    public ByteMixer() {
        this.MIN_BIT_TO_CHANGE = 1;
        this.MAX_BIT_TO_CHANGE = 7;
    }

    public ByteMixer(byte[] phraseInByte, int minByte, int maxByte) {
        this.phraseInByte = phraseInByte;
        this.MIN_BIT_TO_CHANGE = minByte;
        this.MAX_BIT_TO_CHANGE = maxByte;
    }

    public byte[] mixBitInByteArray() {
        int bitNumberToChange = getDigitForChangeByte();

        for (int i = 0; i < phraseInByte.length; i++) {
            phraseInByte[i] = (byte) (phraseInByte[i] ^ 1 << bitNumberToChange);
        }
        return this.phraseInByte;
    }

    public byte[] changeBitsInBytes(byte[] bytesToChange) {
        byte[] result = new byte[bytesToChange.length];
        for (int i = 0; i < bytesToChange.length; i++) {
            result[i] = changeOneBitInByte(bytesToChange[i]);
        }
        return result;
    }

    public byte changeOneBitInByte(byte byteToChange) {
        int mask = 1 << getNumberOfBitToChange();
        return (byte) (byteToChange ^ mask);
    }

    public int getNumberOfBitToChange() {
        return (int) (Math.random() * MAX_BIT_TO_CHANGE);
    }

    public int getDigitForChangeByte() {
        return (int) (Math.random() * MAX_BIT_TO_CHANGE - MIN_BIT_TO_CHANGE) + 1;
    }





}
