package xyz.moonlightpanel.nativeapp.api.raw.auth;

import xyz.moonlightpanel.nativeapp.api.AbstractRequest;
import xyz.moonlightpanel.nativeapp.api.RequestDataBuilder;
import xyz.moonlightpanel.nativeapp.api.ResponseDataContext;

public class RegisterRequest extends AbstractRequest {
    @Override
    public int getId() {
        return 4;
    }

    @Override
    public RequestDataBuilder buildRequest(RequestDataBuilder dataBuilder) {
        return null;
    }

    @Override
    public void readResponse(ResponseDataContext dataContext) {

    }

    @Override
    public void handleData() {

    }

    @Override
    public void clear() {

    }
}
