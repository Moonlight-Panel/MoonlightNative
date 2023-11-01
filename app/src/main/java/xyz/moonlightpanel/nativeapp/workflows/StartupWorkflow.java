package xyz.moonlightpanel.nativeapp.workflows;

import xyz.moonlightpanel.nativeapp.api.ApiClient;
import xyz.moonlightpanel.nativeapp.api.clean.AccountManagementApi;
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

        LayoutManager.showNavigation();
        LayoutManager.hideLoadingIndicator();

        if(accountManager.isLoggedIn()){
            NavigationManager.Companion.getInstance().showPage("/Dashboard");
        }
        else {
            NavigationManager.Companion.getInstance().showPage("/Login");
        }
    }
}
