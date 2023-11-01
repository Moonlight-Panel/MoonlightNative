package xyz.moonlightpanel.nativeapp.api;

import xyz.moonlightpanel.nativeapp.DelegateT;

public abstract class AbstractRequest {
    public abstract int getId();
    public abstract RequestDataBuilder buildRequest(RequestDataBuilder dataBuilder);
    public abstract void readResponse(ResponseDataContext dataContext);
    public abstract void handleData();
    public abstract void clear();
}
