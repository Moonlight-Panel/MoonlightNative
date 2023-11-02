package xyz.moonlightpanel.nativeapp.api.raw.account;

import xyz.moonlightpanel.nativeapp.api.AbstractRequest;
import xyz.moonlightpanel.nativeapp.api.RequestDataBuilder;
import xyz.moonlightpanel.nativeapp.api.ResponseDataContext;
import xyz.moonlightpanel.nativeapp.api.models.AccountData;
import xyz.moonlightpanel.nativeapp.storage.AppStorage;

public class AccountDataRequest extends AbstractRequest {
    @Override
    public int getId() {
        return 6;
    }

    @Override
    public RequestDataBuilder buildRequest(RequestDataBuilder builder) {
        AccountData data = AppStorage.INSTANCE.load("AccountData");
        assert data != null;

        builder.writeBoolean(data.dataChange);
        if(data.dataChange){
            builder.writeString(data.email);
            builder.writeString(data.username);
            builder.writeString(data.newPassword);
            builder.writeString(data.totpConfirmCode);
            builder.writeBoolean(data.totpEnabled);
        }

        return builder;
    }

    @Override
    public void readResponse(ResponseDataContext dataContext) {
        AccountData data = AppStorage.INSTANCE.load("AccountData");
        assert data != null;

        data.dataChange = dataContext.readBoolean();
        data.email = dataContext.readString();
        data.username = dataContext.readString();
        data.newPassword = dataContext.readString();
        data.totpConfirmCode = dataContext.readString();
        data.totpEnabled = dataContext.readBoolean();

        AppStorage.INSTANCE.save("AccountData", data);
    }

    @Override
    public void handleData() {

    }

    @Override
    public void clear() {

    }
}
