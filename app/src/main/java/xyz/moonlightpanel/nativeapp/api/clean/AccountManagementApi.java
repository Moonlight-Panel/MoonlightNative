package xyz.moonlightpanel.nativeapp.api.clean;

import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import xyz.moonlightpanel.nativeapp.Delegate;
import xyz.moonlightpanel.nativeapp.DelegateT;
import xyz.moonlightpanel.nativeapp.api.ApiClient;
import xyz.moonlightpanel.nativeapp.api.models.AccountData;
import xyz.moonlightpanel.nativeapp.api.models.LoginRequestData;
import xyz.moonlightpanel.nativeapp.api.models.LoginResponseData;
import xyz.moonlightpanel.nativeapp.api.models.RegisterRequestData;
import xyz.moonlightpanel.nativeapp.api.raw.auth.TokenBasedLoginRequest;
import xyz.moonlightpanel.nativeapp.lang.Langpack;
import xyz.moonlightpanel.nativeapp.lang.Locale;
import xyz.moonlightpanel.nativeapp.storage.AppStorage;
import xyz.moonlightpanel.nativeapp.workflows.Workflow;

public class AccountManagementApi {
    private ApiClient client;
    private boolean isLoggedIn = false;
    public AccountManagementApi(ApiClient client){
        this.client = client;
    }

    public void loadData(Delegate onStart, Delegate onFinish){
        setAccountData(new AccountData());

        client.exec(6, (t) -> onFinish.invoke(), onStart);
    }

    public void save(Delegate onStart, Delegate onFinish){
        AccountData d = getAccountData();
        d.dataChange = true;
        setAccountData(d);

        client.exec(6, (t) -> onFinish.invoke(), onStart);
    }

    public AccountData getAccountData(){
        return AppStorage.INSTANCE.load("AccountData");
    }
    public void setAccountData(AccountData data){
        AppStorage.INSTANCE.save("AccountData", data);
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void login(String email, String password, String _2fa, Delegate onStart, Delegate onFinish) {
        LoginRequestData data = new LoginRequestData();
        data.email =email.trim();
        data.password = password.trim();
        data.twoFactorCode = _2fa.trim();
        AppStorage.INSTANCE.save("LoginRequestData", data);

        client.exec(3, (t) -> {
            Workflow.trigger(Workflow.LOGIN_RESPONSE);
            onFinish.invoke();
        }, onStart);
    }

    public void register(String email, String username, String password, String confirmPassword, Delegate onStart, Delegate onFinish) {
        RegisterRequestData data = new RegisterRequestData();
        data.email =email.trim();
        data.password = password.trim();
        data.username = username.trim();
        data.passwordConfirm = confirmPassword.trim();
        AppStorage.INSTANCE.save("RegisterRequestData", data);

        client.exec(4, (t) -> {
            Workflow.trigger(Workflow.REGISTER_RESPONSE);
            onFinish.invoke();
        }, onStart);
    }

    public void loginIfTokenExists(Delegate onStart, Delegate onFinish){
        client.exec(2, t -> {
            isLoggedIn = ((TokenBasedLoginRequest)t).isSuccess();
            onFinish.invoke();
        }, onStart);
    }

    public void requestVerifyEmail(Delegate onStart, Delegate onFinish){
        AppStorage.INSTANCE.save("SendMail", true);

        client.exec(5, t -> {
            onFinish.invoke();
        }, onStart);
    }

    public void loadIsVerifiedEmail(Delegate onStart, Delegate onFinish){
        AppStorage.INSTANCE.save("SendMail", false);

        client.exec(5, t -> {
            onFinish.invoke();
        }, onStart);
    }
}
