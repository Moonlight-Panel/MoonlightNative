package xyz.moonlightpanel.nativeapp.ui.theme;

public class ThemeVariantBuilder {
    private ThemeVariant variant;
    public ThemeVariantBuilder(){
        variant = new ThemeVariant();
    }

    public ThemeVariant build(){
        return variant;
    }

    public ThemeVariantBuilder withName(String name){
        variant.setName(name);

        return this;
    }

    public ThemeVariantBuilder isDarkTheme(boolean isDarkTheme){
        variant.setIsDarkTheme(isDarkTheme);

        return this;
    }

    public ThemeVariantBuilder withCorrespondingTheme(String name){
        variant.setCorrespondingTheme(name);

        return this;
    }

    public ThemeVariantBuilder withItem(IThemeVariantBuilderAddItemRef ref){
        ThemeVariantItemBuilder b = new ThemeVariantItemBuilder();
        ThemeVariantItem item = ref.addItem(b);
        variant.addItem(item);

        return this;
    }
}
