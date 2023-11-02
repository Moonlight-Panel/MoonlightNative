package xyz.moonlightpanel.nativeapp.ui.viewmodels;

import xyz.moonlightpanel.nativeapp.Delegate;
import xyz.moonlightpanel.nativeapp.ui.pages.ViewModelManager;

public class LoginViewModel {
    public LoginViewModel() {
        ViewModelManager.clearMethods.add(() -> {
            email = "";
            password = "";
        });
    }

    public String email = "";
    public String password = "";
    public String totp = "";
    public String totpError = "";

    public static LoginViewModel INSTANCE = new LoginViewModel();
}
