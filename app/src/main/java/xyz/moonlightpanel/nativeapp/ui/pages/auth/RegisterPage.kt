package xyz.moonlightpanel.nativeapp.ui.pages.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import xyz.moonlightpanel.nativeapp.Delegate
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager
import xyz.moonlightpanel.nativeapp.ui.components.display.MlHeader

@Composable
fun RegisterPage(){
    Box(modifier = Modifier.fillMaxSize()){
        Column {
            MlHeader(text = "Register")
        }
    }
}

fun RegisterPagePreloader(onFinish: Delegate){
    LayoutManager.hideNavigation()

    onFinish.invoke()
}

@Preview
@Composable
fun RegisterPagePreview(){
    RegisterPage()
}