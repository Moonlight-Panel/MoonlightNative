package xyz.moonlightpanel.nativeapp.api;

import xyz.moonlightpanel.nativeapp.api.raw.PingRequest;

public class ApiTools {
    public static void registerRequests(){
        ApiClient client = ApiClient.INSTANCE;

        client.addRequest(new PingRequest());
    }
}
