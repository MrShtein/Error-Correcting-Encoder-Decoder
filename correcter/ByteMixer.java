package correcter;

public class ByteMixer {

    private final byte[] phraseInByte;
    private final int MINBYTETOCHANGE;
    private final int MAXBYTETOCHANGE;

    public ByteMixer(byte[] phraseInByte, int minByte, int maxByte) {
        this.phraseInByte = phraseInByte;
        this.MINBYTETOCHANGE = minByte;
        this.MAXBYTETOCHANGE = maxByte;
    }

    public byte[] mixBitInByteArray() {
        int bitNumberToChange = getDigitForChangeByte();

        for (int i = 0; i < phraseInByte.length; i++) {
            phraseInByte[i] = (byte) (phraseInByte[i] ^ 1 << bitNumberToChange);
        }
        return this.phraseInByte;
    }

    public int getDigitForChangeByte() {
        return (int) (Math.random() * MAXBYTETOCHANGE - MINBYTETOCHANGE) + 2;
    }





}
