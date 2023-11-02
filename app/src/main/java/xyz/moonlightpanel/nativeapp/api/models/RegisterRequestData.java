package xyz.moonlightpanel.nativeapp.api.models;

import java.io.Serializable;

public class RegisterRequestData implements Serializable {
    public String username;
    public String email;
    public String password;
    public String passwordConfirm;
}
