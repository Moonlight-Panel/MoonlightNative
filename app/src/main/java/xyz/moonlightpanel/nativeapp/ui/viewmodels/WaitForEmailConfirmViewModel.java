package xyz.moonlightpanel.nativeapp.ui.viewmodels;

import xyz.moonlightpanel.nativeapp.storage.AppStorage;
import xyz.moonlightpanel.nativeapp.ui.pages.ViewModelManager;

public class WaitForEmailConfirmViewModel {
    public static WaitForEmailConfirmViewModel INSTANCE = new WaitForEmailConfirmViewModel();

    public WaitForEmailConfirmViewModel(){
        ViewModelManager.clearMethods.add(() -> {

        });
    }

    public boolean isMailSent() {
        return Boolean.TRUE.equals(AppStorage.INSTANCE.load("MailSent"));
    }

    public void setCheckFailed(boolean checkFailed) {
        AppStorage.INSTANCE.save("MailCheckFailed", checkFailed);
    }

    public void setMailSent(boolean mailSent) {
        AppStorage.INSTANCE.save("MailSent", mailSent);
    }

    public void setResent(boolean resent) {
        AppStorage.INSTANCE.save("MailResent", resent);
    }

    public boolean isCheckFailed() {
        return Boolean.TRUE.equals(AppStorage.INSTANCE.load("MailCheckFailed"));
    }

    public boolean isResent() {
        return Boolean.TRUE.equals(AppStorage.INSTANCE.load("MailResent"));
    }

    public boolean isEmailVerified(){
        return Boolean.TRUE.equals(AppStorage.INSTANCE.load("MailVerified"));
    }
}
