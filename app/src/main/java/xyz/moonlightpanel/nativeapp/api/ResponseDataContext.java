package xyz.moonlightpanel.nativeapp.api;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ResponseDataContext {
    private byte[] data;
    private int position;

    public ResponseDataContext(byte[] data)
    {
        this.data = data;
        position = 0;
    }

    private byte[] range(int start, int end){
        return Arrays.copyOfRange(data, start, end);
    }

    private byte[] take(int size){
        byte[] arr = range(position, position + size);
        position += size;

        return arr;
    }

    public int readInt()
    {
        byte[] bytes = take(4);
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.put(bytes);
        buffer.rewind();
        int val = buffer.getInt();
        buffer.clear();
        return val;
    }

    public byte readByte()
    {
        return take(1)[0];
    }

    public boolean readBoolean()
    {
        byte b = readByte();

        return b == (byte)255;
    }

    public String readString()
    {
        int len = readInt();

        byte[] bytes = take(len);

        return new String(bytes, StandardCharsets.UTF_8);
    }
}
