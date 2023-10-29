package xyz.moonlightpanel.nativeapp.ui.accessor;

import xyz.moonlightpanel.nativeapp.Delegate;

public class LayoutManager {
    public static Delegate _showNav;
    public static Delegate _hideNav;

    public static void showNavigation(){
        _showNav.invoke();
    }

    public static void hideNavigation(){
        _hideNav.invoke();
    }
}
