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
                .withTranslation("register.error_0", "")
                .withTranslation("register.error_1", "This email address is already in use")
                .withTranslation("register.error_2", "This email username is already in use")
                .withTranslation("register.error_3", "The passwords need to match")
                .withTranslation("register.error_4", "A password needs to be provided")
                .withTranslation("register.error_5", "The password has to be between 8 und 256 characters")
                .withTranslation("register.error_6", "Usernames can only contain lowercase characters and numbers")
                .withTranslation("register.error_7", "The username has to be between 7 and 20 characters")
                .withTranslation("register.error_8", "An username needs to be provided")
                .withTranslation("register.error_9", "An email has to be provided")
                .withTranslation("register.error_10", "This email address is invalid")
                .withTranslation("email.verify", "Verify email")
                .withTranslation("email.toverify", "Your email needs to be verified. Click the button to send the verification mail.")
                .withTranslation("email.verifysent", "The mail was sent. Check or inbox and the spam folder and click the link.")
                .withTranslation("email.send", "Send mail")
                .withTranslation("email.resend", "Resend mail")
                .withTranslation("email.refresh", "Refresh")
                .withTranslation("email.resent", "Email was successfully resent")
                .withTranslation("email.notverified", "The email address is not yet verified")
                .withTranslation("email.appexplore", "Open app")
                .withTranslation("email.verifysuccess", "Your email has successfully been verified. You can now use the app.")
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
                .withTranslation("register.error_0", "")
                .withTranslation("register.error_1", "Diese Email Adresse wird bereits verwendet")
                .withTranslation("register.error_2", "Dieser Benutzername wird bereits verwendet")
                .withTranslation("register.error_3", "Die Passwörter müssen übereinstimmen")
                .withTranslation("register.error_4", "Es muss ein Passwort gesetzt werden")
                .withTranslation("register.error_5", "Das Passwort muss zwischen 8 und 256 Zeichen lang sein")
                .withTranslation("register.error_6", "Der Benutzername darf nur aus Kleinbuchstaben und Zahlen bestehen")
                .withTranslation("register.error_7", "Der Benutzername muss zwischen 7 und 20 Zeichen lang sein")
                .withTranslation("register.error_8", "Es muss ein Nutzername angegeben werden")
                .withTranslation("register.error_9", "Es muss eine Email Adresse angegeben werden")
                .withTranslation("register.error_10", "Die eingegebene Email Adresse ist ungültig")
                .withTranslation("email.verify", "Email bestätigen")
                .withTranslation("email.toverify", "Du musst deine Email verifizieren. Klicke den Button, um eine Email mit einem Link zur Verifizierung zu senden.")
                .withTranslation("email.verifysent", "Die Mail wurde gesendet. Schau in deinem Posteingang nach und gegebenenfalls im Spam-Ordner und klicke auf den Link.")
                .withTranslation("email.send", "Email senden")
                .withTranslation("email.resend", "Email erneut senden")
                .withTranslation("email.refresh", "Aktualisieren")
                .withTranslation("email.resent", "Die Email wurde erfolgreich erneut gesendet")
                .withTranslation("email.notverified", "Die Email Adresse ist noch nicht verifiziert")
                .withTranslation("email.appexplore", "App öffnen")
                .withTranslation("email.verifysuccess", "Deine Email Adresse wurde erfolgreich verifiziert. Du kannst nun auf die App zugreifen.")
                .build());
    }
}
