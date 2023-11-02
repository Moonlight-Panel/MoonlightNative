package xyz.moonlightpanel.nativeapp.ui.components.display

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt

@Composable
fun MlLabel(text: String, withPadding: Boolean = true, overrideColor: Color = Color.Transparent){
    val theme = DynamicTheme.getCurrentTheme();
    var col = theme.getItem("Label::Color").asColor().kt()
    var px = theme.getItem("App::ContentPadding").asDouble()
    if(!withPadding)
        px = 0.0
    if(overrideColor != Color.Transparent)
        col = overrideColor
    Box(modifier = Modifier.padding(horizontal = px.dp).padding(top = (px / 2).dp)) {
        Text(text = text, color = col)
    }
}

@Preview
@Composable
fun MlLabelPreview(){
    MlLabel(text = "Hello World!")
}