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
                .withTranslation("pages.login", "Sign in")
                .withTranslation("pages.register", "Sign up")
                .withTranslation("navigation.dashboard", "Dashboard")
                .withTranslation("navigation.store", "Store")
                .withTranslation("navigation.services", "Services")
                .withTranslation("navigation.community", "Community")
                .withTranslation("navigation.account", "Me")
                .withTranslation("account.username", "Username")
                .withTranslation("account.email", "Email")
                .withTranslation("account.password", "Password")
                .withTranslation("account.save", "Save")
                .withTranslation("login.failed", "No user with these credential combination found")
                .withTranslation("login.advertisement", "Sign in and manage your services from all around the world")
                .build());
        locales.add(new LocaleBuilder()
                .withName("de_de")
                .withTranslation("pages.account", "Benutzerkonto")
                .withTranslation("pages.login", "Anmelden")
                .withTranslation("pages.register", "Registrieren")
                .withTranslation("navigation.dashboard", "Dashboard")
                .withTranslation("navigation.store", "Store")
                .withTranslation("navigation.services", "Dienste")
                .withTranslation("navigation.community", "Community")
                .withTranslation("navigation.account", "Konto")
                .withTranslation("account.username", "Benutzername")
                .withTranslation("account.email", "E-Mail")
                .withTranslation("account.password", "Passwort")
                .withTranslation("account.save", "Speichern")
                .withTranslation("login.failed", "Es konnte kein Benutzer mit diesen Daten gefunden werden")
                .withTranslation("login.advertisement", "Melde dich an und verwalte deine Dienste von überall")
                .build());
    }
}
