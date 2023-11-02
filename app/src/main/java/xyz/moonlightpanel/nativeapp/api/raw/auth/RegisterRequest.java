package xyz.moonlightpanel.nativeapp.api.raw.auth;

import xyz.moonlightpanel.nativeapp.api.AbstractRequest;
import xyz.moonlightpanel.nativeapp.api.RequestDataBuilder;
import xyz.moonlightpanel.nativeapp.api.ResponseDataContext;
import xyz.moonlightpanel.nativeapp.api.models.RegisterRequestData;
import xyz.moonlightpanel.nativeapp.api.models.RegisterResponseData;
import xyz.moonlightpanel.nativeapp.lang.Langpack;
import xyz.moonlightpanel.nativeapp.lang.Locale;
import xyz.moonlightpanel.nativeapp.storage.AppStorage;

public class RegisterRequest extends AbstractRequest {
    @Override
    public int getId() {
        return 4;
    }

    @Override
    public RequestDataBuilder buildRequest(RequestDataBuilder builder) {
        RegisterRequestData data = AppStorage.INSTANCE.load("RegisterRequestData");
        assert data != null;

        builder.writeString(data.email);
        builder.writeString(data.username);
        builder.writeString(data.password);
        builder.writeString(data.passwordConfirm);

        return builder;
    }

    @Override
    public void readResponse(ResponseDataContext dataContext) {
        Locale lang = Langpack.INSTANCE.getLocale();
        RegisterResponseData data = new RegisterResponseData();

        data.success = dataContext.readBoolean();
        data.emailVerifyRequired = dataContext.readBoolean();
        data.error = lang.get("register.error_" + dataContext.readInt());
        data.token = dataContext.readString();

        AppStorage.INSTANCE.save("RegisterResponseData", data);
    }

    @Override
    public void handleData() {

    }

    @Override
    public void clear() {

    }
}
