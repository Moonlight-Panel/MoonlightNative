package xyz.moonlightpanel.nativeapp.workflows;

import android.util.Log;

import xyz.moonlightpanel.nativeapp.api.ApiClient;
import xyz.moonlightpanel.nativeapp.api.clean.AccountManagementApi;
import xyz.moonlightpanel.nativeapp.api.models.LoginResponseData;
import xyz.moonlightpanel.nativeapp.storage.AppStorage;
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager;
import xyz.moonlightpanel.nativeapp.ui.accessor.NavigationManager;

public class LoginResponseWorkflow extends Workflow{
    @Override
    void run() {
        LoginResponseData data = AppStorage.INSTANCE.load("LoginResponseData");
        AccountManagementApi accountManager = ApiClient.INSTANCE.getAccountManagementApi();
        assert data != null;

        if(data.success){
            AppStorage.INSTANCE.set("token", data.token);
            accountManager.loadIsVerifiedEmail(LayoutManager::showLoadingIndicator, () -> {
                LayoutManager.hideLoadingIndicator();
                boolean verified = Boolean.TRUE.equals(AppStorage.INSTANCE.load("MailVerified"));
                Log.d("WSXD", "" + verified);
                if(verified){
                    LayoutManager.showNavigation();
                    String lastPage = (!AppStorage.INSTANCE.get("LastPage").isEmpty() ? AppStorage.INSTANCE.get("LastPage") :  "/Dashboard");

                    if(lastPage.contains("Register")
                            ||lastPage.contains("Login")
                            ||lastPage.contains("EmailConfirm")
                            ||lastPage.contains("ConnectionError")
                            ||lastPage.contains("Totp"))
                        lastPage = "/Dashboard";

                    NavigationManager.Companion.getInstance().showPage(lastPage);
                }
                else {
                    NavigationManager.Companion.getInstance().showPage("/WaitForEmailConfirm");
                }
            });

            AppStorage.INSTANCE.remove("LoginRequestData");
            AppStorage.INSTANCE.remove("LoginResponseData");
        }
        else if(data.requireTotp) {
            NavigationManager.Companion.getInstance().showPage("/EnterTotpCode");
            LayoutManager.hideNavigation();
        }
        else {
            LayoutManager.hideNavigation();
        }
    }
}
