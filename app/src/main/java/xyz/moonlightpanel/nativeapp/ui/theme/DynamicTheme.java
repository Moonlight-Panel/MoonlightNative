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
                .withItem(builder -> builder
                        .withId("App::ContentPadding")
                        .withDouble(15)
                        .withDescription("padding for the components")
                        .build())
                .withItem(builder -> builder
                        .withId("App::Success")
                        .withColor(80,205,137,255)
                        .withDescription("Default success color")
                        .build())
                .withItem(builder -> builder
                        .withId("App::Danger")
                        .withColor(241,65,108,255)
                        .withDescription("Default danger color")
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
                // Box border colors
                .withItem(builder -> builder
                        .withId("Box::Light/Border")
                        .withColor(43,43,64,255)
                        .withDescription("Border color for all light boxes")
                        .build())
                .withItem(builder -> builder
                        .withId("Box::Primary/Border")
                        .withColor(0,158,247,255)
                        .withDescription("Border color for all primary boxes")
                        .build())
                .withItem(builder -> builder
                        .withId("Box::Dark/Border")
                        .withColor(71,71,97,255)
                        .withDescription("Border color for all dark boxes")
                        .build())
                .withItem(builder -> builder
                        .withId("Box::Secondary/Border")
                        .withColor(50,50,72,255)
                        .withDescription("Border color for all secondary boxes")
                        .build())
                .withItem(builder -> builder
                        .withId("Box::Success/Border")
                        .withColor(80,205,137,255)
                        .withDescription("Border color for all success boxes")
                        .build())
                .withItem(builder -> builder
                        .withId("Box::Danger/Border")
                        .withColor(241,65,108,255)
                        .withDescription("Border color for all danger boxes")
                        .build())
                .withItem(builder -> builder
                        .withId("Box::Warning/Border")
                        .withColor(255,199,0,255)
                        .withDescription("Border color for all warning boxes")
                        .build())
                .withItem(builder -> builder
                        .withId("Box::Info/Border")
                        .withColor(114, 57, 234,255)
                        .withDescription("Border color for all info boxes")
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
                .withItem(builder -> builder
                        .withId("Navigation::IconSize")
                        .withDouble(30)
                        .withDescription("size of the navigation bar items")
                        .build())
                .withItem(builder -> builder
                        .withId("Navigation::Height")
                        .withDouble(72)
                        .withDescription("height of the navigation bar")
                        .build())
                .withItem(builder -> builder
                        .withId("Navigation::LoaderSize")
                        .withDouble(72)
                        .withDescription("size of the loader")
                        .build())
                .withItem(builder -> builder
                        .withId("Navigation::LoaderPadding")
                        .withDouble(36)
                        .withDescription("top padding of the loader")
                        .build())
                .withItem(builder -> builder
                        .withId("Navigation::LoaderDuration")
                        .withDouble(1500)
                        .withDescription("duration of the loader animation")
                        .build())
                .withItem(builder -> builder
                        .withId("Navigation::LoaderColor")
                        .withColor(62,151,255,255)
                        .withDescription("color of the loader")
                        .build())
                // Header Component
                .withItem(builder -> builder
                        .withId("Header::Color")
                        .withColor(255,255,255,255)
                        .withDescription("color of the header text")
                        .build())
                .withItem(builder -> builder
                        .withId("Header::FontSize")
                        .withDouble(42)
                        .withDescription("font size of the header text")
                        .build())
                // Label Component
                .withItem(builder -> builder
                        .withId("Label::Color")
                        .withColor(71, 71, 97,255)
                        .withDescription("color of the label text")
                        .build())
                // Link Component
                .withItem(builder -> builder
                        .withId("Link::Color")
                        .withColor(100,171,255,255)
                        .withDescription("color of the link text")
                        .build())
                // TextBox Component
                .withItem(builder -> builder
                        .withId("TextBox::PaddingX")
                        .withDouble(15)
                        .withDescription("horizontal padding of the text box component")
                        .build())
                .withItem(builder -> builder
                        .withId("TextBox::PaddingY")
                        .withDouble(8)
                        .withDescription("vertical padding of the text box component")
                        .build())
                .withItem(builder -> builder
                        .withId("TextBox::TopPaddingOverflow")
                        .withDouble(1.5)
                        .withDescription("value is added to the top padding")
                        .build())
                .withItem(builder -> builder
                        .withId("TextBox::FocusPlaceholderPadding")
                        .withDouble(2)
                        .withDescription("padding of the placeholder in focus state")
                        .build())
                .withItem(builder -> builder
                        .withId("TextBox::BorderColor")
                        .withColor(71,71,97,255)
                        .withDescription("border color of the text box component")
                        .build())
                .withItem(builder -> builder
                        .withId("TextBox::PlaceholderColor")
                        .withColor(86,86,116,255)
                        .withDescription("placeholder color of the text box component")
                        .build())
                .withItem(builder -> builder
                        .withId("TextBox::TextColor")
                        .withColor(146,146,159,255)
                        .withDescription("text color of the text box component")
                        .build())
                // Account Page
                .withItem(builder -> builder
                        .withId("AccountPage::Error")
                        .withColor(241,65,108,255)
                        .withDescription("error color for the account page")
                        .build())
                // Subheader component
                .withItem(builder -> builder
                        .withId("Subheader::Color")
                        .withColor(255,255,255,255)
                        .withDescription("color of the subheader text")
                        .build())
                .build();
    }
}
