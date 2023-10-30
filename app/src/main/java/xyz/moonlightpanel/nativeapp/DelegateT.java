package xyz.moonlightpanel.nativeapp;

public interface DelegateT<T>{
    void invoke(T t);
}