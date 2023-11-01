package xyz.moonlightpanel.nativeapp.api.raw.auth;

import xyz.moonlightpanel.nativeapp.api.AbstractRequest;
import xyz.moonlightpanel.nativeapp.api.RequestDataBuilder;
import xyz.moonlightpanel.nativeapp.api.ResponseDataContext;
import xyz.moonlightpanel.nativeapp.api.models.LoginRequestData;
import xyz.moonlightpanel.nativeapp.api.models.LoginResponseData;
import xyz.moonlightpanel.nativeapp.lang.Langpack;
import xyz.moonlightpanel.nativeapp.lang.Locale;
import xyz.moonlightpanel.nativeapp.storage.AppStorage;

public class CredentialBasedLoginRequest extends AbstractRequest {
    @Override
    public int getId() {
        return 3;
    }

    @Override
    public RequestDataBuilder buildRequest(RequestDataBuilder dataBuilder) {
        LoginRequestData data = AppStorage.INSTANCE.load("LoginRequestData");
        dataBuilder.writeString(data.email);
        dataBuilder.writeString(data.password);
        dataBuilder.writeString(data.twoFactorCode);
        return dataBuilder;
    }

    @Override
    public void readResponse(ResponseDataContext dataContext) {
        Locale lang = Langpack.INSTANCE.getLocale();

        LoginResponseData data = new LoginResponseData();
        data.success = dataContext.readBoolean();
        data.requireTotp = dataContext.readBoolean();
        data.error = lang.get("login.error_" + dataContext.readInt());
        data.token = dataContext.readString();

        AppStorage.INSTANCE.save("LoginResponseData", data);
    }

    @Override
    public void handleData() {

    }

    @Override
    public void clear() {

    }
}
