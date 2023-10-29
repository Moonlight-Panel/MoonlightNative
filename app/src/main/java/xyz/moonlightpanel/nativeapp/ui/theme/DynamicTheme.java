package xyz.moonlightpanel.nativeapp.ui.theme;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Objects;

import xyz.moonlightpanel.nativeapp.ui.layout.MainLayoutKt;

public class DynamicTheme {
    private static String currentTheme = "default";
    private static boolean isInit = false;
    private static ArrayList<ThemeVariant> themes;

    public static ArrayList<ThemeVariant> getThemes() {
        init();
        return themes;
    }

    @Nullable
    public static ThemeVariant getTheme(String name){
        for(ThemeVariant t: getThemes()){
            if(Objects.equals(t.getName(), name))
                return t;
        }

        return null;
    }

    public static ThemeVariant getCurrentTheme(){
        return getTheme(currentTheme);
    }

    public void setCurrentTheme(@NonNull ThemeVariant theme){
        currentTheme = theme.getName();
    }

    private static void init(){
        if(isInit)
            return;
        isInit = true;
        themes = new ArrayList<>();
        themes.add(getDefault());
        //TODO: add themes from files

    }

    public static ThemeVariant getDefault(){
        return new ThemeVariantBuilder()
                .withName("default")
                .isDarkTheme(true)
                .withItem(builder -> builder
                        .withId("App::bg")
                        .withColor(21,21,33, (byte) 255)
                        .withDescription("Background color of the App")
                        .build())
                // Button dimensions
                .withItem(builder -> builder
                        .withId("Button::px")
                        .withDouble(15)
                        .withDescription("Padding for the X-Dimension of the button component")
                        .build())
                .withItem(builder -> builder
                        .withId("Button::py")
                        .withDouble(8)
                        .withDescription("Padding for the Y-Dimension of the button component")
                        .build())
                .withItem(builder -> builder
                        .withId("Button::mx")
                        .withDouble(15)
                        .withDescription("Margin for the X-Dimension of the button component")
                        .build())
                .withItem(builder -> builder
                        .withId("Button::my")
                        .withDouble(8)
                        .withDescription("Margin for the Y-Dimension of the button component")
                        .build())
                // Button border colors
                .withItem(builder -> builder
                        .withId("Button::Text")
                        .withColor(255,255,255,255)
                        .withDescription("Text color for all buttons")
                        .build())
                .withItem(builder -> builder
                        .withId("Button::Light/Border")
                        .withColor(43,43,64,255)
                        .withDescription("Border color for all light buttons")
                        .build())
                .withItem(builder -> builder
                        .withId("Button::Primary/Border")
                        .withColor(0,158,247,255)
                        .withDescription("Border color for all primary buttons")
                        .build())
                .withItem(builder -> builder
                        .withId("Button::Dark/Border")
                        .withColor(71,71,97,255)
                        .withDescription("Border color for all dark buttons")
                        .build())
                .withItem(builder -> builder
                        .withId("Button::Secondary/Border")
                        .withColor(50,50,72,255)
                        .withDescription("Border color for all secondary buttons")
                        .build())
                .withItem(builder -> builder
                        .withId("Button::Success/Border")
                        .withColor(80,205,137,255)
                        .withDescription("Border color for all success buttons")
                        .build())
                .withItem(builder -> builder
                        .withId("Button::Danger/Border")
                        .withColor(241,65,108,255)
                        .withDescription("Border color for all danger buttons")
                        .build())
                .withItem(builder -> builder
                        .withId("Button::Warning/Border")
                        .withColor(255,199,0,255)
                        .withDescription("Border color for all warning buttons")
                        .build())
                .withItem(builder -> builder
                        .withId("Button::Info/Border")
                        .withColor(114, 57, 234,255)
                        .withDescription("Border color for all info buttons")
                        .build())
                // Navigation
                .withItem(builder -> builder
                        .withId("Navigation::Background")
                        .withColor(30,30,45,255)
                        .withDescription("Background color of the navigation bar")
                        .build())
                .withItem(builder -> builder
                        .withId("Navigation::IconColor")
                        .withColor(86,86,116,255)
                        .withDescription("Color of the navigation bar icons")
                        .build())
                .withItem(builder -> builder
                        .withId("Navigation::TextColor")
                        .withColor(146,146,159,255)
                        .withDescription("Color of the navigation bar text")
                        .build())
                .withItem(builder -> builder
                        .withId("Navigation::ClickColor")
                        .withColor(1,1,3,255)
                        .withDescription("Color of the navigation bar click animation")
                        .build())
                .build();
    }
}
