package xyz.moonlightpanel.nativeapp.api.raw.auth;

import xyz.moonlightpanel.nativeapp.api.AbstractRequest;
import xyz.moonlightpanel.nativeapp.api.RequestDataBuilder;
import xyz.moonlightpanel.nativeapp.api.ResponseDataContext;
import xyz.moonlightpanel.nativeapp.storage.AppStorage;

public class TokenBasedLoginRequest extends AbstractRequest {
    private boolean success = false;
    @Override
    public int getId() {
        return 2;
    }

    @Override
    public RequestDataBuilder buildRequest(RequestDataBuilder dataBuilder) {
        String token = AppStorage.INSTANCE.get("token");
        dataBuilder.writeString(token);
        return dataBuilder;
    }

    @Override
    public void readResponse(ResponseDataContext dataContext) {
        success = dataContext.readBoolean();
    }

    @Override
    public void handleData() {

    }

    @Override
    public void clear() {
        success = false;
    }

    public boolean isSuccess() {
        return success;
    }
}
