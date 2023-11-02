package xyz.moonlightpanel.nativeapp.ui.pages.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.moonlightpanel.nativeapp.Delegate
import xyz.moonlightpanel.nativeapp.api.ApiClient
import xyz.moonlightpanel.nativeapp.lang.Langpack
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager
import xyz.moonlightpanel.nativeapp.ui.accessor.NavigationManager
import xyz.moonlightpanel.nativeapp.ui.components.display.MlHeader
import xyz.moonlightpanel.nativeapp.ui.components.display.MlLabel
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButton
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButtonType
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt
import xyz.moonlightpanel.nativeapp.ui.viewmodels.WaitForEmailConfirmViewModel

@Composable
fun WaitForEmailConfirmPage(){
    val lang = Langpack.INSTANCE.locale
    val api = ApiClient.INSTANCE.accountManagementApi
    val viewModel = WaitForEmailConfirmViewModel.INSTANCE
    val theme = DynamicTheme.getCurrentTheme()
    
    val successColor = theme.getItem("App::Success").asColor().kt()
    val errorColor = theme.getItem("App::Danger").asColor().kt()
    val cp = theme.getItem("App::ContentPadding").asDouble()

    var mailSent by remember {
        mutableStateOf(viewModel.isMailSent)
    }

    var resent by remember {
        mutableStateOf(viewModel.isResent)
    }

    var checkFailed by remember {
        mutableStateOf(viewModel.isCheckFailed)
    }

    var emailVerified by remember {
        mutableStateOf(viewModel.isEmailVerified)
    }

    Box(modifier = Modifier.fillMaxSize()){
        Column {
            MlHeader(text = lang["email.verify"])
            if (mailSent && !emailVerified){
                MlLabel(text = lang["email.verifysent"])
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height((cp / 2).dp))
                MlButton(text = lang["email.resend"], type = MlButtonType.Warning, onClick = {
                    api.requestVerifyEmail({
                        LayoutManager.showLoadingIndicator()
                    }, {
                        viewModel.isMailSent = true
                        viewModel.isCheckFailed = false
                        viewModel.isResent = true

                        mailSent = viewModel.isMailSent
                        checkFailed = viewModel.isCheckFailed
                        resent = viewModel.isResent
                        emailVerified = viewModel.isEmailVerified
                        LayoutManager.hideLoadingIndicator()
                    })
                    resent = true
                })
                MlButton(text = lang["email.refresh"], type = MlButtonType.Success, onClick = {
                    api.loadIsVerifiedEmail({
                        LayoutManager.showLoadingIndicator()
                    },{
                        viewModel.isCheckFailed = !viewModel.isEmailVerified
                        viewModel.isResent = false
                        viewModel.isMailSent = true

                        mailSent = viewModel.isMailSent
                        checkFailed = viewModel.isCheckFailed
                        resent = viewModel.isResent
                        emailVerified = viewModel.isEmailVerified
                        LayoutManager.hideLoadingIndicator()
                    })
                })
                if(resent && !emailVerified){
                    MlLabel(text = lang["email.resent"], overrideColor = successColor)
                }
                if(checkFailed && !emailVerified){
                    MlLabel(text = lang["email.notverified"], overrideColor = errorColor)
                }
            }
            else if(emailVerified){
                MlLabel(text = lang["email.verifysuccess"])
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height((cp / 4).dp))
                MlButton(text = lang["email.appexplore"], type = MlButtonType.Primary, onClick = {
                    LayoutManager.showNavigation()
                    NavigationManager.instance.showPage("/Dashboard")
                })
            }
            else {
                MlLabel(text = lang["email.toverify"])
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height((cp / 2).dp))
                MlButton(text = lang["email.send"], type = MlButtonType.Primary, onClick = {
                    api.requestVerifyEmail({
                        LayoutManager.showLoadingIndicator()
                    }, {
                        viewModel.isMailSent = true
                        viewModel.isCheckFailed = false
                        viewModel.isResent = false

                        mailSent = viewModel.isMailSent
                        checkFailed = viewModel.isCheckFailed
                        resent = viewModel.isResent
                        emailVerified = viewModel.isEmailVerified
                        LayoutManager.hideLoadingIndicator()
                    })
                })
            }
        }
    }
}

fun WaitForEmailConfirmPagePreloader(onFinish: Delegate){
    LayoutManager.hideNavigation()
    onFinish.invoke()
}

@Preview
@Composable
fun WaitForEmailConfirmPagePreview(){
    WaitForEmailConfirmPage()
}