package xyz.moonlightpanel.nativeapp.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import xyz.moonlightpanel.nativeapp.Delegate
import xyz.moonlightpanel.nativeapp.ui.components.display.MlHeader

@Composable
fun PageTemplate(){
    Box(modifier = Modifier.fillMaxSize()){
        Column {
            MlHeader(text = "PageTemplate")
        }
    }
}

fun PageTemplatePreloader(onFinish: Delegate){

    onFinish.invoke()
}

@Preview
@Composable
fun PageTemplatePreview(){
    PageTemplate()
}