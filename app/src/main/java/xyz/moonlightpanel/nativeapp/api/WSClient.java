package xyz.moonlightpanel.nativeapp.api;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;

public class WSClient extends WebSocketClient {
    private ApiClient client;
    public WSClient(URI serverUri, ApiClient c) {
        super(serverUri);
        client = c;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        client.onOpen(handshakedata);
    }

    @Override
    public void onMessage(String message) {
        client.onMessage(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        client.onClose(code, reason, remote);
    }

    @Override
    public void onError(Exception ex) {

    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        client.onMessage(bytes);
    }


}
