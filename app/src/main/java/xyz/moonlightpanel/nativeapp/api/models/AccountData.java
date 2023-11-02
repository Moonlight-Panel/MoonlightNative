package xyz.moonlightpanel.nativeapp.api.models;

import java.io.Serializable;

public class AccountData implements Serializable {
    public String email = "";
    public String username = "";
    public String saveError = "";
    public boolean totpEnabled;
    public String totpConfirmCode = "";
    public String newPassword = "";
    public boolean dataChange;
    public String totpKey = "";
    public String qrCode = "";
    public String avatarUrl = "";
}
