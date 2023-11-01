package xyz.moonlightpanel.nativeapp.ui.pages.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import xyz.moonlightpanel.nativeapp.Delegate
import xyz.moonlightpanel.nativeapp.R
import xyz.moonlightpanel.nativeapp.api.ApiClient
import xyz.moonlightpanel.nativeapp.lang.Langpack
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager
import xyz.moonlightpanel.nativeapp.ui.accessor.NavigationManager
import xyz.moonlightpanel.nativeapp.ui.components.display.MlHeader
import xyz.moonlightpanel.nativeapp.ui.components.display.MlLabel
import xyz.moonlightpanel.nativeapp.ui.components.display.MlLink
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButton
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButtonType
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlTextBox
import xyz.moonlightpanel.nativeapp.ui.components.interaction.passwordCensoring
import xyz.moonlightpanel.nativeapp.ui.pages.ViewModelManager
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt
import xyz.moonlightpanel.nativeapp.ui.viewmodels.LoginViewModel
import xyz.moonlightpanel.nativeapp.ui.viewmodels.RegisterViewModel

@Composable
fun RegisterPage(){
    val lang = Langpack.INSTANCE.locale
    val apiClient = ApiClient.INSTANCE
    val accountManager = apiClient.accountManagementApi

    val viewModel = RegisterViewModel.INSTANCE
    var error by remember {
        mutableStateOf(accountManager.registerError)
    }

    val theme = DynamicTheme.getCurrentTheme()
    val errorColor = theme.getItem("AccountPage::Error").asColor().kt()
    val contentPadding = theme.getItem("App::ContentPadding").asDouble()

    val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())) {
            MlHeader(text = lang["pages.register"])
            MlLabel(text = lang["register.advertisement"])
            Image(painter = painterResource(id = R.drawable.endelonlogo), contentDescription = "Endelon Logo",
                Modifier
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally))
            MlTextBox(placeholder = lang["account.username"], value = viewModel.username, onValueChanged = {viewModel.username = it})
            MlTextBox(placeholder = lang["account.email"], value = viewModel.email, onValueChanged = {viewModel.email = it})
            MlTextBox(placeholder = lang["account.password"], value = viewModel.password, onValueChanged = {viewModel.password = it}, showTextRenderer = passwordCensoring)
            MlTextBox(placeholder = lang["account.passwordconfirm"], value = viewModel.passwordConfirm, onValueChanged = {viewModel.passwordConfirm = it}, showTextRenderer = passwordCensoring)
            if(error != "")
                Text(
                    text = error, modifier = Modifier
                        .padding(horizontal = contentPadding.dp),
                    color = errorColor
                )
            MlButton(text = lang["pages.register"], type = MlButtonType.Primary, onClick = {
                accountManager.register(viewModel.email, viewModel.username, viewModel.password, viewModel.passwordConfirm, "", {
                    LayoutManager.showLoadingIndicator()
                }, {
                    uiScope.launch {
                        LayoutManager.hideLoadingIndicator()

                        if(it){
                            NavigationManager.instance.showPage("/WaitForEmailConfirm")
                            LayoutManager.hideNavigation()
                        }
                        else {
                            error = accountManager.registerError
                        }
                    }
                })
            })
            Row(modifier = Modifier
                .align(Alignment.End)
                .padding(bottom = contentPadding.dp)
                .padding(end = contentPadding.dp)) {
                MlLabel(text = lang["register.alreadyregistered"] + " ", withPadding = false)
                MlLink(text = lang["register.login"], target = "/Login")
            }
        }
    }
}

fun RegisterPagePreloader(onFinish: Delegate){
    LayoutManager.hideNavigation()
    ViewModelManager.clearAll()

    onFinish.invoke()
}

@Preview
@Composable
fun RegisterPagePreview(){
    RegisterPage()
}