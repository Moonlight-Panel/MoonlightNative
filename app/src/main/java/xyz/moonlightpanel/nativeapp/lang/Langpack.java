package xyz.moonlightpanel.nativeapp.lang;

import java.util.ArrayList;

public class Langpack {
    public static Langpack INSTANCE = new Langpack();

    public Langpack(){
        locales = new ArrayList<>();
        addDefaultLocales();
        //TODO: Load current locale from file
    }
    private int currentLocale = 1;
    private ArrayList<Locale> locales;

    public ArrayList<Locale> getLocales() {
        return locales;
    }

    public Locale getLocale(){
        return locales.get(currentLocale);
    }

    public void setLocale(Locale locale){
        currentLocale = locales.indexOf(locale);
        //TODO: Save to file
    }

    private void addDefaultLocales(){
        locales.add(new LocaleBuilder()
                .withName("en_us")
                .withTranslation("pages.account", "Account")
                .withTranslation("navigation.dashboard", "Dashboard")
                .withTranslation("navigation.store", "Store")
                .withTranslation("navigation.services", "Services")
                .withTranslation("navigation.community", "Community")
                .withTranslation("navigation.account", "Me")
                .build());
        locales.add(new LocaleBuilder()
                .withName("de_de")
                .withTranslation("pages.account", "Benutzerkonto")
                .withTranslation("navigation.dashboard", "Dashboard")
                .withTranslation("navigation.store", "Store")
                .withTranslation("navigation.services", "Dienste")
                .withTranslation("navigation.community", "Community")
                .withTranslation("navigation.account", "Konto")
                .build());
    }
}
