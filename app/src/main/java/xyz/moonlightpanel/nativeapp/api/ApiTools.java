package xyz.moonlightpanel.nativeapp.api;

import xyz.moonlightpanel.nativeapp.api.raw.PingRequest;
import xyz.moonlightpanel.nativeapp.api.raw.account.AccountDataRequest;
import xyz.moonlightpanel.nativeapp.api.raw.auth.CredentialBasedLoginRequest;
import xyz.moonlightpanel.nativeapp.api.raw.auth.IsEmailVerifiedRequest;
import xyz.moonlightpanel.nativeapp.api.raw.auth.RegisterRequest;
import xyz.moonlightpanel.nativeapp.api.raw.auth.TokenBasedLoginRequest;

public class ApiTools {
    public static void registerRequests(){
        ApiClient client = ApiClient.INSTANCE;

        client.addRequest(new PingRequest());
        client.addRequest(new CredentialBasedLoginRequest());
        client.addRequest(new RegisterRequest());
        client.addRequest(new TokenBasedLoginRequest());
        client.addRequest(new IsEmailVerifiedRequest());
        client.addRequest(new AccountDataRequest());
    }
}
