package xyz.moonlightpanel.nativeapp.api.models;

import java.io.Serializable;

public class RegisterResponseData implements Serializable {
    public boolean success;
    public boolean emailVerifyRequired;
    public String token;
    public String error;
}
