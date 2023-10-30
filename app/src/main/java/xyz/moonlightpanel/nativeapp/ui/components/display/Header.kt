package xyz.moonlightpanel.nativeapp.ui.components.display

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt

@Composable
fun Header(text: String){
    val theme = DynamicTheme.getCurrentTheme();
    val col = theme.getItem("Header::Color").asColor().kt()
    val size = theme.getItem("Header::FontSize").asDouble()
    val px = theme.getItem("App::ContentPadding").asDouble()
    Box(modifier = Modifier.padding(horizontal = px.dp).padding(top = px.dp)) {
        Text(text = text, color = col, fontSize = size.sp)
    }
}

@Preview
@Composable
fun HeaderPreview(){
    Header(text = "Hello World!")
}