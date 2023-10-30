package xyz.moonlightpanel.nativeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import xyz.moonlightpanel.nativeapp.ui.accessor.NavigationManager
import xyz.moonlightpanel.nativeapp.ui.layout.MainLayout
import xyz.moonlightpanel.nativeapp.ui.pages.*
import xyz.moonlightpanel.nativeapp.ui.pages.auth.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NavigationManager.instance.registerPage("/Login", { LoginPage() }, { t -> LoginPagePreloader(t) })
        NavigationManager.instance.registerPage("/Register", { RegisterPage() }, { t -> RegisterPagePreloader(t) })
        NavigationManager.instance.registerPage("/Account", { AccountPage() }, { t -> AccountPagePreloader(t) })
        NavigationManager.instance.registerPage("/Community") { CommunityPage() }
        NavigationManager.instance.registerPage("/Dashboard") { DashboardPage() }
        NavigationManager.instance.registerPage("/Services") { ServicesPage() }
        NavigationManager.instance.registerPage("/Store") { StorePage() }

        setContent {
            MainLayout()
        }
    }
}