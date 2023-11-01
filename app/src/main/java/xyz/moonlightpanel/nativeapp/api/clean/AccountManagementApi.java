package xyz.moonlightpanel.nativeapp.api.clean;

import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import xyz.moonlightpanel.nativeapp.Delegate;
import xyz.moonlightpanel.nativeapp.DelegateT;
import xyz.moonlightpanel.nativeapp.api.ApiClient;
import xyz.moonlightpanel.nativeapp.lang.Langpack;
import xyz.moonlightpanel.nativeapp.lang.Locale;

public class AccountManagementApi {
    private ApiClient client;
    private String username;
    private String email;
    private String[] error = new String[0];
    private String loginError = "";
    private String registerError = "";
    private boolean isLoggedIn = false;
    private boolean loaded = false;
    public AccountManagementApi(ApiClient client){
        this.client = client;
    }

    public void loadData(Delegate onStart, Delegate onFinish){
        if(loaded){
            onFinish.invoke();
            return;
        }

        /*client.exec(() -> {
            loaded = true;
            username = "helloWorldUsername";
            email = "helloWorld@hello.world";
            isLoggedIn = false;

            client.triggerMockDataLoadingAnimation();
        }, onFinish, onStart);*/
    }

    public void save(Delegate onStart, DelegateT<String[]> onFinish){
        /*client.exec(() -> {
            client.triggerMockDataLoadingAnimation();
        }, () ->{
            String[] error = new String[]{"Error 1", "Error 2"};
            this.error = error;
            onFinish.invoke(error);
        }, () -> {
            error = new String[0];
            onStart.invoke();
        });*/
    }

    public String getUsername() {
        return username;
    }

    public String[] getError() {
        return error;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getRegisterError() {
        return registerError;
    }

    public void login(String email, String password, Delegate onStart, DelegateT<Boolean> onFinish) {
        AtomicBoolean loginActionSuccess = new AtomicBoolean(false);
        /*client.exec(() -> {
            client.triggerMockDataLoadingAnimation();
            if (email.trim().equals("hello@world") && password.trim().equals("hello")) {
                this.email = email;
                loginActionSuccess.set(true);
            }
            else {
                loginActionSuccess.set(false);
            }
        }, () -> {
            Locale lang = Langpack.INSTANCE.getLocale();
            loginError = loginActionSuccess.get() ? "" : lang.get("login.failed");
            onFinish.invoke(loginActionSuccess.get());
        }, onStart);*/
    }

    public void register(String email, String username, String password, String confirmPassword, String _2faCode, Delegate onStart, DelegateT<Boolean> onFinish) {
        AtomicBoolean loginActionSuccess = new AtomicBoolean(false);
        /*client.exec(() -> {
            client.triggerMockDataLoadingAnimation();
            if (email.trim().equals("hello@world") && password.trim().equals("hello")) {
                this.email = email;
                loginActionSuccess.set(true);
            }
            else {
                loginActionSuccess.set(false);
            }
        }, () -> {
            Locale lang = Langpack.INSTANCE.getLocale();
            registerError = loginActionSuccess.get() ? "" : lang.get("login.failed");
            onFinish.invoke(loginActionSuccess.get());
        }, onStart);*/
    }
}
