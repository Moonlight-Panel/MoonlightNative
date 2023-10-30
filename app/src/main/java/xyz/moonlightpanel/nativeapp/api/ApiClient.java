package xyz.moonlightpanel.nativeapp.api;

import xyz.moonlightpanel.nativeapp.Delegate;
import xyz.moonlightpanel.nativeapp.api.clean.AccountManagementApi;
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager;

public class ApiClient {
    public ApiClient(){
        accountManagementApi = new AccountManagementApi(this);
    }

    private AccountManagementApi accountManagementApi;

    public AccountManagementApi getAccountManagementApi() {
        return accountManagementApi;
    }

    public static ApiClient INSTANCE = new ApiClient();

    public void triggerMockDataLoadingAnimation(){
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void exec(Delegate apiFunction, Delegate onFinish, Delegate onStart){
        Thread t = null;
        Runnable r = () -> {
            onStart.invoke();

            apiFunction.invoke();

            onFinish.invoke();
            Thread.currentThread().interrupt();
        };
        t = new Thread(r);
        t.start();
    }
}
