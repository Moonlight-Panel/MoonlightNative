package xyz.moonlightpanel.nativeapp.ui.pages

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import xyz.moonlightpanel.nativeapp.Delegate
import xyz.moonlightpanel.nativeapp.api.ApiClient
import xyz.moonlightpanel.nativeapp.lang.Langpack
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager
import xyz.moonlightpanel.nativeapp.ui.components.display.MlHeader
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButton
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButtonType
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlTextBox
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt

@Composable
fun AccountPage() {
    val apiClient = ApiClient.INSTANCE;
    val accountManager = apiClient.accountManagementApi;

    val lang = Langpack.INSTANCE.locale
    var error by remember {
        mutableStateOf(accountManager.error.joinToString("\n"))
    }

    val theme = DynamicTheme.getCurrentTheme()
    val contentPadding = theme.getItem("App::ContentPadding").asDouble()
    val errorColor = theme.getItem("AccountPage::Error").asColor().kt()

    val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            MlHeader(text = lang.translate("pages.account"))
            Text(text = "Avatar will be implemented someday")
            //TODO
            MlTextBox(placeholder = lang["account.username"],
                value = accountManager.username,
                onValueChanged = { accountManager.username = it })
            MlTextBox(placeholder = lang["account.email"],
                value = accountManager.email,
                onValueChanged = { accountManager.email = it })

            if(error != "")
                Text(
                    text = error, modifier = Modifier
                        .padding(horizontal = contentPadding.dp),
                    color = errorColor
                )

            MlButton(text = lang["account.save"], type = MlButtonType.Primary, onClick = {
                accountManager.save({
                    LayoutManager.showLoadingIndicator()
                }, {
                    uiScope.launch {
                        error = it.joinToString("\n")
                        LayoutManager.hideLoadingIndicator()
                        Log.d("XTC", error)
                    }
                })
            })
        }
    }
}

fun AccountPagePreloader(onFinish: Delegate){
    val apiClient = ApiClient.INSTANCE

    val accountManager = apiClient.accountManagementApi

    accountManager.loadData({
        LayoutManager.showLoadingIndicator()
    }, {
        LayoutManager.hideLoadingIndicator()
        LayoutManager.showNavigation()
        onFinish.invoke()
    })
}

@Preview
@Composable
fun AccountPagePreview(){
    val apiClient = ApiClient.INSTANCE
    apiClient.setInPreview()
    val accountManager = apiClient.accountManagementApi
    accountManager.loadData({},{})
    AccountPage()
}