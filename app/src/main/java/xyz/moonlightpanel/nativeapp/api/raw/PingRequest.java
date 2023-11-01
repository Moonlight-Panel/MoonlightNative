package xyz.moonlightpanel.nativeapp.api.raw;

import android.util.Log;

import xyz.moonlightpanel.nativeapp.api.AbstractRequest;
import xyz.moonlightpanel.nativeapp.api.RequestDataBuilder;
import xyz.moonlightpanel.nativeapp.api.ResponseDataContext;

public class PingRequest extends AbstractRequest {
    private int chunk;

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public RequestDataBuilder buildRequest(RequestDataBuilder builder) {
        builder.writeInt(6969);

        return builder;
    }

    @Override
    public void readResponse(ResponseDataContext dataContext) {
        chunk = dataContext.readInt();
    }

    @Override
    public void handleData() {
        Log.d("WSX", "" + chunk);
    }

    @Override
    public void clear() {
        chunk = 0;
    }

    public int getChunk() {
        return chunk;
    }
}
