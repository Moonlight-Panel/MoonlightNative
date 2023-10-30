package xyz.moonlightpanel.nativeapp.api.clean;

import xyz.moonlightpanel.nativeapp.Delegate;
import xyz.moonlightpanel.nativeapp.api.ApiClient;

public class AccountManagementApi {
    private ApiClient client;
    private String username;
    private String email;
    private boolean loaded = false;
    public AccountManagementApi(ApiClient client){
        this.client = client;
    }

    public void loadData(Delegate onStart, Delegate onFinish){
        if(loaded){
            onFinish.invoke();
            return;
        }

        client.exec(() -> {
            loaded = true;
            username = "helloWorldUsername";
            email = "helloWorld@hello.world";

            client.triggerMockDataLoadingAnimation();
        }, onFinish, onStart);
    }

    public String getUsername() {
        return username;
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
}
