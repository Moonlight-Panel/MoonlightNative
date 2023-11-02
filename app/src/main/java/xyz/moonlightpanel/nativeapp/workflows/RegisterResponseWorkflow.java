package xyz.moonlightpanel.nativeapp.workflows;

import xyz.moonlightpanel.nativeapp.api.models.LoginResponseData;
import xyz.moonlightpanel.nativeapp.api.models.RegisterResponseData;
import xyz.moonlightpanel.nativeapp.storage.AppStorage;
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager;
import xyz.moonlightpanel.nativeapp.ui.accessor.NavigationManager;

public class RegisterResponseWorkflow extends Workflow{
    @Override
    void run() {
        RegisterResponseData data = AppStorage.INSTANCE.load("RegisterResponseData");
        assert data != null;

        if(data.success){
            AppStorage.INSTANCE.set("token", data.token);
            AppStorage.INSTANCE.remove("RegisterRequestData");
            AppStorage.INSTANCE.remove("RegisterResponseData");

            if(data.emailVerifyRequired){
                NavigationManager.Companion.getInstance().showPage("/WaitForEmailConfirm");
                LayoutManager.hideNavigation();
            }
            else {
                NavigationManager.Companion.getInstance().showPage("/Dashboard");
                LayoutManager.showNavigation();
            }
        }
        else {
            LayoutManager.hideNavigation();
        }
    }
}
