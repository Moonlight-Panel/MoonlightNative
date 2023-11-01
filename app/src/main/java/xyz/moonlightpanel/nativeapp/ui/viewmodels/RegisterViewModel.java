package xyz.moonlightpanel.nativeapp.ui.viewmodels;

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

    public static RegisterViewModel INSTANCE = new RegisterViewModel();
}
