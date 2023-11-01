package xyz.moonlightpanel.nativeapp.api.models;

import java.io.Serializable;

public class LoginResponseData implements Serializable {
    public boolean success;
    public boolean requireTotp;
    public String error;
    public String token;
}
