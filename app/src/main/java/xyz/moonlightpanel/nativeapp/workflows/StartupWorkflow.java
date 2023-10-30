package xyz.moonlightpanel.nativeapp.workflows;

import xyz.moonlightpanel.nativeapp.api.ApiClient;
import xyz.moonlightpanel.nativeapp.api.clean.AccountManagementApi;
import xyz.moonlightpanel.nativeapp.ui.accessor.NavigationManager;

public class StartupWorkflow extends Workflow {
    @Override
    void run() {
        ApiClient apiClient = ApiClient.INSTANCE;
        AccountManagementApi accountManager = apiClient.getAccountManagementApi();

        if(accountManager.isLoggedIn()){
            NavigationManager.Companion.getInstance().showPage("/Dashboard");
        }
        else {
            NavigationManager.Companion.getInstance().showPage("/Login");
        }
    }
}
