package xyz.moonlightpanel.nativeapp.ui.pages.auth

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import xyz.moonlightpanel.nativeapp.Delegate
import xyz.moonlightpanel.nativeapp.api.ApiClient
import xyz.moonlightpanel.nativeapp.api.models.LoginResponseData
import xyz.moonlightpanel.nativeapp.lang.Langpack
import xyz.moonlightpanel.nativeapp.storage.AppStorage
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager
import xyz.moonlightpanel.nativeapp.ui.components.display.MlHeader
import xyz.moonlightpanel.nativeapp.ui.components.display.MlLabel
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButton
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButtonType
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlTextBox
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt
import xyz.moonlightpanel.nativeapp.ui.viewmodels.LoginViewModel

@Composable
fun EnterTotpCodePage(){
    val viewModel = LoginViewModel.INSTANCE
    val lang = Langpack.INSTANCE.locale
    val theme = DynamicTheme.getCurrentTheme()
    val accountManager = ApiClient.INSTANCE.accountManagementApi

    val errorColor = theme.getItem("App::Danger").asColor().kt()
    val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    var error by remember {
        mutableStateOf(viewModel.totpError)
    }

    val execLoginCode = {
        Log.e("APX", viewModel.email + " " + viewModel.password + " " + viewModel.totp)
        accountManager.login(viewModel.email, viewModel.password, viewModel.totp, {
            LayoutManager.showLoadingIndicator()
        }, {
            uiScope.launch {
                LayoutManager.hideLoadingIndicator()
                val lastResponse = AppStorage.INSTANCE.load<LoginResponseData>("LoginResponseData")
                error = if (lastResponse == null) "" else viewModel.totpError
            }
        })
    }

    Box(modifier = Modifier.fillMaxSize()){
        Column {
            MlHeader(text = lang["totp.2fa"])
            MlLabel(text = lang["totp.info"])
            MlTextBox(placeholder = lang["totp.code"], onValueChanged = {
                viewModel.totp = it
                if(it.length == 6)
                    execLoginCode()
            })
            if(error.isNotEmpty()){
                MlLabel(text = error, overrideColor = errorColor)
            }
            MlButton(text = lang["totp.login"], type = MlButtonType.Primary, onClick = execLoginCode)
        }
    }
}

fun EnterTotpCodePagePreloader(onFinish: Delegate){

    onFinish.invoke()
}

@Preview
@Composable
fun EnterTotpCodePagePreview(){
    EnterTotpCodePage()
}