package xyz.moonlightpanel.nativeapp.api.models;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class LoginRequestData implements Serializable {
    public String email = "";
    public String password = "";
    public String twoFactorCode = "";
}
