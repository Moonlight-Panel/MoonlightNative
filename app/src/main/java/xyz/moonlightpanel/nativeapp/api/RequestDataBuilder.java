package xyz.moonlightpanel.nativeapp.api;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class RequestDataBuilder {
    private final ArrayList<Byte> data;

    public RequestDataBuilder()
    {
        data = new ArrayList<>();
    }

    private void addRange(byte[] data){
        for (byte b : data)
            this.data.add(b);
    }

    public void writeInt(int data)
    {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(data);
        buffer.rewind();
        byte[] bytes = buffer.array();
        buffer.clear();
        addRange(bytes);
    }

    public void writeByte(byte data)
    {
        this.data.add(data);
    }

    public void writeBoolean(boolean data)
    {
        writeByte(data ? (byte)255 : (byte)0);
    }

    public void writeString(String data)
    {
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        int len = bytes.length;

        writeInt(len);
        addRange(bytes);
    }

    public byte[] toBytes()
    {
        byte[] arr = new byte[data.size()];

        for (int i = 0; i < data.size(); i++)
            arr[i] = data.get(i);

        return arr;
    }
}
