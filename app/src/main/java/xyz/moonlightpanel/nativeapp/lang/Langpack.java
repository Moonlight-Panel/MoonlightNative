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
                .withTranslation("account.passwordconfirm", "Repeat your password")
                .withTranslation("account.save", "Save")
                .withTranslation("login.failed", "No user with these credential combination found")
                .withTranslation("login.advertisement", "Sign in and manage your services from all around the world")
                .withTranslation("login.notregistered", "Not signed up yet?")
                .withTranslation("login.register", "Sign up")
                .withTranslation("register.advertisement", "Sign up and let the adventure begin")
                .withTranslation("register.alreadyregistered", "Already signed up?")
                .withTranslation("register.login", "Sign in")
                .withTranslation("connection.error", "Connection error")
                .withTranslation("connection.error_hint", "Make sure that you are connected to the internet and that Endelon Hosting is reachable.")
                .withTranslation("connection.reconnect", "Reconnect")
                .withTranslation("login.error_0", "")
                .withTranslation("login.error_1", "The given credential do not match any account")
                .withTranslation("login.error_2", "2FA code required")
                .withTranslation("login.error_3", "2FA key is missing. Please contact the support to fix your account")
                .withTranslation("login.error_4", "2FA code is invalid")
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
                .withTranslation("login.notregistered", "Noch nicht registriert?")
                .withTranslation("login.register", "Registrieren")
                .withTranslation("register.advertisement", "Registriere dich und lasse das Abenteuer beginnen")
                .withTranslation("account.passwordconfirm", "Passwort wiederholen")
                .withTranslation("register.alreadyregistered", "Bereits registriert?")
                .withTranslation("register.login", "Anmelden")
                .withTranslation("connection.error", "Verbindungsfehler")
                .withTranslation("connection.error_hint", "Stelle sicher, dass du mit dem Internet verbunden bist und Endelon Hosting erreichen kannst.")
                .withTranslation("connection.reconnect", "Erneut verbinden")
                .withTranslation("login.error_0", "")
                .withTranslation("login.error_1", "Es wurde kein Konto mit diesen Zugangsdaten gefunden")
                .withTranslation("login.error_2", "Ein 2FA Code ist erfoderlich")
                .withTranslation("login.error_3", "Der 2FA Key fehlt. Kontaktiere den Support, um dieses Problem zu beheben.")
                .withTranslation("login.error_4", "Der 2FA Code ist ungültig")
                .build());
    }
}
