package xyz.moonlightpanel.nativeapp.workflows;

import xyz.moonlightpanel.nativeapp.api.ApiClient;
import xyz.moonlightpanel.nativeapp.api.clean.AccountManagementApi;
import xyz.moonlightpanel.nativeapp.storage.AppStorage;
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager;
import xyz.moonlightpanel.nativeapp.ui.accessor.NavigationManager;

public class StartupWorkflow extends Workflow {
    ApiClient apiClient = ApiClient.INSTANCE;
    @Override
    void run() {
        LayoutManager.hideNavigation();

        apiClient.startClient(LayoutManager::showLoadingIndicator, (b) -> {
            if(!b){
                LayoutManager.hideLoadingIndicator();
                LayoutManager.hideNavigation();
                NavigationManager.Companion.getInstance().showPage("/ConnectionError");
                return;
            }

            apiClient.runTestRequest(() -> {}, (t) -> {
                if(!t){
                    LayoutManager.hideLoadingIndicator();
                    LayoutManager.hideNavigation();
                    NavigationManager.Companion.getInstance().showPage("/ConnectionError");
                    return;
                }

                apiClient.getAccountManagementApi().loginIfTokenExists(() -> {}, this::afterConnect);
            });
        });
    }

    void afterConnect(){
        AccountManagementApi accountManager = apiClient.getAccountManagementApi();

        if(accountManager.isLoggedIn()){
            accountManager.loadIsVerifiedEmail(LayoutManager::showLoadingIndicator, () -> {
                LayoutManager.hideLoadingIndicator();
                boolean verified = Boolean.TRUE.equals(AppStorage.INSTANCE.load("MailVerified"));

                if(verified){
                    LayoutManager.showNavigation();
                    String lastPage = (!AppStorage.INSTANCE.get("LastPage").isEmpty() ? AppStorage.INSTANCE.get("LastPage") :  "/Dashboard");

                    if(lastPage.contains("Register")
                            ||lastPage.contains("ConnectionError")
                            ||lastPage.contains("Login")
                            ||lastPage.contains("EmailConfirm")
                            ||lastPage.contains("Totp"))
                        lastPage = "/Dashboard";

                    NavigationManager.Companion.getInstance().showPage(lastPage);
                }
                else {
                    NavigationManager.Companion.getInstance().showPage("/WaitForEmailConfirm");
                }
            });
        }
        else {
            LayoutManager.hideLoadingIndicator();
            NavigationManager.Companion.getInstance().showPage("/Login");
        }
    }
}
