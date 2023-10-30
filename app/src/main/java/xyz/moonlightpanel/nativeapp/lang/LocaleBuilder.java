package xyz.moonlightpanel.nativeapp.lang;

public class LocaleBuilder {
    private Locale locale;

    public LocaleBuilder(){
        locale = new Locale();
    }

    public LocaleBuilder withName(String name){
        locale.setName(name);

        return this;
    }

    public LocaleBuilder withTranslation(String key, String value){
        locale.set(key, value);

        return this;
    }

    public Locale build(){
        return locale;
    }
}
