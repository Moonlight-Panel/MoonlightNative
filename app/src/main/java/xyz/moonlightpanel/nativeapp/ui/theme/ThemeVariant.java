package xyz.moonlightpanel.nativeapp.ui.theme;

import java.util.ArrayList;
import java.util.Objects;

public class ThemeVariant {
    private String name;
    private boolean isDarkTheme;
    private String correspondingTheme;
    private ArrayList<ThemeVariantItem> items;

    public ThemeVariant(){
        items = new ArrayList<>();
    }

    public String getCorrespondingTheme() {
        return correspondingTheme;
    }

    public String getName() {
        return name;
    }

    public boolean isDarkTheme() {
        return isDarkTheme;
    }
    public void setIsDarkTheme(boolean b) {
        isDarkTheme = b;
    }

    public void setCorrespondingTheme(String correspondingTheme) {
        this.correspondingTheme = correspondingTheme;
    }

    public void setDarkTheme(boolean darkTheme) {
        isDarkTheme = darkTheme;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addItem(ThemeVariantItem item){
        items.add(item);
    }

    public ThemeVariantItem getItem(String name){
        for (ThemeVariantItem i : items) {
            if(Objects.equals(i.getId(), name))
                return  i;
        }

        return null;
    }

    public ArrayList<ThemeVariantItem> getItems() {
        return items;
    }
}
