package xyz.moonlightpanel.nativeapp.ui.pages;

import java.util.ArrayList;

import xyz.moonlightpanel.nativeapp.Delegate;

public class ViewModelManager {
    public static ArrayList<Delegate> clearMethods = new ArrayList<>();
    public static void clearAll(){
        for (Delegate d : clearMethods)
            d.invoke();
    }
}
