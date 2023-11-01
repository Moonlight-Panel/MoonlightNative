package xyz.moonlightpanel.nativeapp.workflows;

import xyz.moonlightpanel.nativeapp.api.models.LoginResponseData;
import xyz.moonlightpanel.nativeapp.storage.AppStorage;
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager;
import xyz.moonlightpanel.nativeapp.ui.accessor.NavigationManager;

public class LoginResponseWorkflow extends Workflow{
    @Override
    void run() {
        LoginResponseData data = AppStorage.INSTANCE.load("LoginResponseData");
        assert data != null;

        if(data.success){
            AppStorage.INSTANCE.set("token", data.token);

            NavigationManager.Companion.getInstance().showPage("/Dashboard");
            AppStorage.INSTANCE.remove("LoginRequestData");
            AppStorage.INSTANCE.remove("LoginResponseData");
            LayoutManager.showNavigation();
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
