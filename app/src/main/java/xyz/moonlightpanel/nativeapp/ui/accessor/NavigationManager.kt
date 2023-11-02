package xyz.moonlightpanel.nativeapp.ui.accessor

import android.util.Log
import androidx.compose.runtime.Composable
import xyz.moonlightpanel.nativeapp.Delegate
import xyz.moonlightpanel.nativeapp.DelegateT
import xyz.moonlightpanel.nativeapp.storage.AppStorage
import xyz.moonlightpanel.nativeapp.workflows.Workflow

class NavigationManager {
    private var pages: MutableMap<String, @Composable () -> Unit> = mutableMapOf()
    private var dialogs: MutableMap<String, @Composable () -> Unit> = mutableMapOf()
    private var preloaders: MutableMap<String, DelegateT<Delegate>> = mutableMapOf()

    lateinit var __putPage: DelegateT<@Composable () -> Unit>
    lateinit var __putDialog: DelegateT<@Composable () -> Unit>
    lateinit var __destroyDialog: Delegate
    private var init = false

    private var currentPage = ""
    private var isInDialog = false

    fun showPage(path: String){
        if(preloaders.containsKey(path)){
            val preloader = preloaders[path]

            preloader?.invoke(Delegate {
                __putPage(pages[path])
                currentPage = path

                AppStorage.INSTANCE["LastPage"] = currentPage
                Log.w("LLL",AppStorage.INSTANCE["LastPage"])
            })
        }
        else
        {
            __putPage(pages[path])
            currentPage = path
            AppStorage.INSTANCE["LastPage"] = currentPage
            Log.w("LLL",AppStorage.INSTANCE["LastPage"])
        }
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
    fun registerPage(path: String, page: @Composable () -> Unit, preloader: DelegateT<Delegate>){
        pages[path] = page
        preloaders[path] = preloader
    }

    fun registerDialog(path: String, dialog: @Composable () -> Unit){
        dialogs[path] = dialog
    }

    fun finishInit(){
        if(!init){
            init = true
            Workflow.trigger(Workflow.STARTUP)
        }
    }

    companion object {
        val instance = NavigationManager()
    }
}