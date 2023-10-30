package xyz.moonlightpanel.nativeapp.ui.accessor;

import xyz.moonlightpanel.nativeapp.Delegate;
import xyz.moonlightpanel.nativeapp.DelegateT;

public class LayoutManager {
    public static Delegate _showNav;
    public static Delegate _hideNav;
    public static DelegateT<Boolean> _setLoading;

    public static void showNavigation() {
        _showNav.invoke();
    }

    public static void hideNavigation() {
        _hideNav.invoke();
    }

    public static void showLoadingIndicator() {
        try {
            _setLoading.invoke(true);
        } catch (Exception ignored) {

        }
    }

    public static void hideLoadingIndicator() {
        try {
            _setLoading.invoke(false);
        } catch (Exception ignored) {

        }
    }
}
