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
fun MlSubheader(text: String){
    val theme = DynamicTheme.getCurrentTheme();
    val col = theme.getItem("Subheader::Color").asColor().kt()
    val px = theme.getItem("App::ContentPadding").asDouble()
    Box(modifier = Modifier.padding(horizontal = px.dp).padding(top = px.dp / 2)) {
        Text(text = text, color = col)
    }
}

@Preview
@Composable
fun MlSubheaderPreview(){
    MlSubheader(text = "Hello World!")
}