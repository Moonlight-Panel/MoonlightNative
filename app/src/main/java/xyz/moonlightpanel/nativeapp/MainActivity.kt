package xyz.moonlightpanel.nativeapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import xyz.moonlightpanel.nativeapp.api.ApiTools
import xyz.moonlightpanel.nativeapp.storage.AppStorage
import xyz.moonlightpanel.nativeapp.ui.accessor.NavigationManager
import xyz.moonlightpanel.nativeapp.ui.layout.MainLayout
import xyz.moonlightpanel.nativeapp.ui.pages.*
import xyz.moonlightpanel.nativeapp.ui.pages.auth.*
import xyz.moonlightpanel.nativeapp.ui.pages.other.*

class MainActivity : ComponentActivity() {
    companion object {
        var isAppLoaded = false
        var activityReload = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        activityReload = true
        super.onCreate(savedInstanceState)

        if(!isAppLoaded) {
            AppStorage.INSTANCE.contentDirectory = applicationContext.filesDir

            ApiTools.registerRequests()

            NavigationManager.instance.registerPage(
                "/Login",
                { LoginPage() },
                { t -> LoginPagePreloader(t) })
            NavigationManager.instance.registerPage(
                "/Register",
                { RegisterPage() },
                { t -> RegisterPagePreloader(t) })
            NavigationManager.instance.registerPage(
                "/Account",
                { AccountPage() },
                { t -> AccountPagePreloader(t) })
            NavigationManager.instance.registerPage(
                "/ConnectionError",
                { ConnectionErrorPage() },
                { t -> ConnectionErrorPagePreloader(t) })
            NavigationManager.instance.registerPage(
                "/EnterTotpCode",
                { EnterTotpCodePage() },
                { t -> EnterTotpCodePagePreloader(t) })
            NavigationManager.instance.registerPage(
                "/WaitForEmailConfirm",
                { WaitForEmailConfirmPage() },
                { t -> WaitForEmailConfirmPagePreloader(t) })
            NavigationManager.instance.registerPage("/Community") { CommunityPage() }
            NavigationManager.instance.registerPage("/Dashboard") { DashboardPage() }
            NavigationManager.instance.registerPage("/Services") { ServicesPage() }
            NavigationManager.instance.registerPage("/Store") { StorePage() }
        }

        setContent {
            MainLayout()
        }

        isAppLoaded = true;
    }
}