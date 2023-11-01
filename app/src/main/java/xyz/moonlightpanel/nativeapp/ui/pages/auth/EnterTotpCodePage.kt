package xyz.moonlightpanel.nativeapp.ui.pages.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import xyz.moonlightpanel.nativeapp.Delegate
import xyz.moonlightpanel.nativeapp.ui.components.display.MlHeader

@Composable
fun EnterTotpCodePage(){
    Box(modifier = Modifier.fillMaxSize()){
        Column {
            MlHeader(text = "Totp code")
            //TODO: Implement login with 2FA
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