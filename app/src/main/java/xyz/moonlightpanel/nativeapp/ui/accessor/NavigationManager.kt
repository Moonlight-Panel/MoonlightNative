package xyz.moonlightpanel.nativeapp.ui.accessor

import androidx.compose.runtime.Composable
import xyz.moonlightpanel.nativeapp.Delegate
import xyz.moonlightpanel.nativeapp.DelegateT

class NavigationManager {
    var pages: MutableMap<String, @Composable () -> Unit> = mutableMapOf()
    var dialogs: MutableMap<String, @Composable () -> Unit> = mutableMapOf()

    lateinit var __putPage: DelegateT<@Composable () -> Unit>
    lateinit var __putDialog: DelegateT<@Composable () -> Unit>
    lateinit var __destroyDialog: Delegate
    var init = false

    var currentPage = ""
    var isInDialog = false

    fun showPage(path: String){
        __putPage(pages[path])
        currentPage = path
    }
    fun showDialog(path: String){
        isInDialog = true
        __putDialog(dialogs[path])
    }
    fun hideDialog(path: String){
        isInDialog = false
        __destroyDialog()
    }

    fun registerPage(path: String, page: @Composable () -> Unit){
        pages[path] = page
    }

    fun registerDialog(path: String, dialog: @Composable () -> Unit){
        dialogs[path] = dialog
    }

    fun finishInit(){
        if(!init){
            init = true;
            showPage("/Dashboard")
        }
    }

    companion object {
        val instance = NavigationManager()
    }
}