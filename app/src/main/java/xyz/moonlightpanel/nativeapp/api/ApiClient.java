package xyz.moonlightpanel.nativeapp.api;

import android.util.Log;

import androidx.core.util.Function;
import androidx.core.util.Supplier;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.WebSocketListener;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.PingFrame;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


import xyz.moonlightpanel.nativeapp.Delegate;
import xyz.moonlightpanel.nativeapp.DelegateT;
import xyz.moonlightpanel.nativeapp.api.clean.AccountManagementApi;
import xyz.moonlightpanel.nativeapp.api.raw.PingRequest;

public class ApiClient {
    private boolean inPreview = false;
    private WSClient client;
    private HashMap<Integer, AbstractRequest> handlers;
    public ApiClient(){
        accountManagementApi = new AccountManagementApi(this);
        handlers = new HashMap<>();
    }

    public void addRequest(AbstractRequest request){
        handlers.put(request.getId(), request);
    }

    private AccountManagementApi accountManagementApi;

    public AccountManagementApi getAccountManagementApi() {
        return accountManagementApi;
    }

    public static ApiClient INSTANCE = new ApiClient();

    public void triggerMockDataLoadingAnimation(){
        try {
            if(!inPreview)
                Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private DelegateT<byte[]> onNextRequest = null;
    public void exec(int apiFunction, DelegateT<AbstractRequest> onFinish, Delegate onStart){
        Thread t = null;
        Runnable r = () -> {
            onStart.invoke();

            AbstractRequest request = handlers.get(apiFunction);
            assert request != null;

            request.clear();

            RequestDataBuilder rdb = new RequestDataBuilder();
            rdb.writeInt(apiFunction);
            rdb = request.buildRequest(rdb);

            byte[] data = rdb.toBytes();
            try {
                sendMessage(data);

                onNextRequest = (bData) -> {
                    Log.i("WSX", "Handler call");
                    ResponseDataContext rdc = new ResponseDataContext(bData);
                    request.readResponse(rdc);
                    request.handleData();

                    onFinish.invoke(request);

                    onNextRequest = null;
                    Thread.currentThread().interrupt();
                };
            }
            catch (Exception e){

            }
        };
        t = new Thread(r);
        t.start();
    }

    public void setInPreview() {
        inPreview = true;
    }

    private DelegateT<Boolean> onFinishInit;
    public void startClient(Delegate onStart, DelegateT<Boolean> onFinish){
        onFinishInit = onFinish;
        new Thread(() -> {
            try {
                onStart.invoke();
                client = new WSClient(new URI(ApiConstants.API_URL), this);
                boolean b = client.connectBlocking(500, TimeUnit.MILLISECONDS);
                if(!b)
                    throw new Exception("Connection timed out");
            } catch (Exception e) {
                Log.e("EXC", e.toString());
                onFinishInit.invoke(false);
            }
            Thread.currentThread().interrupt();
        }).start();
    }

    public void onOpen(ServerHandshake handshake) {
        onFinishInit.invoke(true);
        Log.d("WSX", handshake.getHttpStatusMessage());
    }

    public void onClose(int code, String reason, boolean remote) {
    }

    public void onMessage(String message) {
        //we don't use strings
        Log.w("WSX", message);
    }

    public void onMessage(ByteBuffer bytes) {
        Log.e("WSX", bytes.array().length + "");
        if(onNextRequest != null)
            onNextRequest.invoke(bytes.array());
        else {
            ResponseDataContext rdc = new ResponseDataContext(bytes.array());
            int id = rdc.readInt();

            AbstractRequest request = handlers.get(id);
            assert request != null;

            request.readResponse(rdc);
            request.handleData();
        }

        bytes.clear();
    }

    public void sendMessage(byte[] message) {
        client.send(message);
    }

    public void runTestRequest(Delegate onStart, DelegateT<Boolean> onFinish){
        exec(1, (t) -> {
            PingRequest r = (PingRequest)t;
            boolean successful = r.getChunk() == 10324;

            onFinish.invoke(successful);
        }, onStart);
    }

    public AbstractRequest getRequest(AbstractRequest randomInstance){
        int id = randomInstance.getId();

        return handlers.get(id);
    }
}
