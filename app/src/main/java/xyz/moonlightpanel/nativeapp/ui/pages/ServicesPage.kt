package xyz.moonlightpanel.nativeapp.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ServicesPage(){
    Box(modifier = Modifier.fillMaxSize()){
        Column {
            Text(text = "Services")
        }
    }
}

@Preview
@Composable
fun ServicesPagePreview(){
    ServicesPage()
}