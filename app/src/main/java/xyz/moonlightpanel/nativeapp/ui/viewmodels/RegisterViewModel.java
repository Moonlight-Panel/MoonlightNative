package xyz.moonlightpanel.nativeapp.ui.viewmodels;

import xyz.moonlightpanel.nativeapp.api.models.RegisterResponseData;
import xyz.moonlightpanel.nativeapp.storage.AppStorage;
import xyz.moonlightpanel.nativeapp.ui.pages.ViewModelManager;

public class RegisterViewModel {
    public RegisterViewModel() {
        ViewModelManager.clearMethods.add(() -> {
            username = "";
            email = "";
            password = "";
            passwordConfirm = "";
        });
    }
    public String email = "";
    public String username = "";
    public String password = "";
    public String passwordConfirm = "";
    public String getError(){
        RegisterResponseData data = AppStorage.INSTANCE.load("RegisterResponseData");
        return data != null ? data.error : "";
    }
    public static RegisterViewModel INSTANCE = new RegisterViewModel();
}
