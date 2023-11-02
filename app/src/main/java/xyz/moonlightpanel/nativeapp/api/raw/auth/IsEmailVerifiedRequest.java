package xyz.moonlightpanel.nativeapp.api.raw.auth;

import xyz.moonlightpanel.nativeapp.api.AbstractRequest;
import xyz.moonlightpanel.nativeapp.api.RequestDataBuilder;
import xyz.moonlightpanel.nativeapp.api.ResponseDataContext;
import xyz.moonlightpanel.nativeapp.storage.AppStorage;

public class IsEmailVerifiedRequest extends AbstractRequest {
    @Override
    public int getId() {
        return 5;
    }

    @Override
    public RequestDataBuilder buildRequest(RequestDataBuilder dataBuilder) {
        boolean sendMail = Boolean.TRUE.equals(AppStorage.INSTANCE.load("SendMail"));

        dataBuilder.writeBoolean(sendMail);
        return dataBuilder;
    }

    @Override
    public void readResponse(ResponseDataContext dataContext) {
        AppStorage.INSTANCE.save("MailVerified", dataContext.readBoolean());
    }

    @Override
    public void handleData() {

    }

    @Override
    public void clear() {

    }
}
