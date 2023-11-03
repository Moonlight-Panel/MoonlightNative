package xyz.moonlightpanel.nativeapp.ui.pages

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import xyz.moonlightpanel.nativeapp.api.models.AccountData
import xyz.moonlightpanel.nativeapp.lang.Langpack
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager
import xyz.moonlightpanel.nativeapp.ui.components.display.MlBox
import xyz.moonlightpanel.nativeapp.ui.components.display.MlBoxColorScheme
import xyz.moonlightpanel.nativeapp.ui.components.display.MlHeader
import xyz.moonlightpanel.nativeapp.ui.components.display.MlLabel
import xyz.moonlightpanel.nativeapp.ui.components.display.MlSubheader
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButton
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButtonType
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlTextBox
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt

@Composable
fun AccountPage() {
    val apiClient = ApiClient.INSTANCE
    val accountManager = apiClient.accountManagementApi

    val lang = Langpack.INSTANCE.locale

    var accountData by remember {
        mutableStateOf(if(accountManager.accountData == null) AccountData() else accountManager.accountData)
    }

    var error by remember {
        mutableStateOf(accountData.saveError)
    }

    val theme = DynamicTheme.getCurrentTheme()
    val contentPadding = theme.getItem("App::ContentPadding").asDouble()
    val errorColor = theme.getItem("AccountPage::Error").asColor().kt()

    val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    Box(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Column {
            MlHeader(text = lang.translate("pages.account"))
            MlBox(colorScheme = MlBoxColorScheme.Info) {
                MlLabel(text = "Avatar will be implemented someday")
                //TODO: Implement Avatar
            }
            MlTextBox(placeholder = lang["account.username"],
                value = accountData.username,
                onValueChanged = { accountData.username = it; accountManager.accountData = accountData })
            MlTextBox(placeholder = lang["account.email"],
                value = accountData.email,
                onValueChanged = { accountData.email = it; accountManager.accountData = accountData })

            if(accountData.totpEnabled) {
                MlBox(colorScheme = MlBoxColorScheme.Warning) {
                    MlSubheader(text = lang["account.deactivate2fa"])
                    MlButton(text = lang["account.deactivate"], type = MlButtonType.Danger)
                }
            }
            else {
                MlBox(colorScheme = MlBoxColorScheme.Info) {
                    MlSubheader(text = lang["account.activate2fa"])
                    MlButton(text = lang["account.activate"], type = MlButtonType.Primary)
                }
            }

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
                        accountData = accountManager.accountData
                        error = accountData.saveError
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