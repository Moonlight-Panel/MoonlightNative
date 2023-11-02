package xyz.moonlightpanel.nativeapp.ui.components.display

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt

@Composable
fun MlBox(colorScheme: MlBoxColorScheme, modifier: Modifier = Modifier, content: @Composable () -> Unit){
    val theme = DynamicTheme.getCurrentTheme()
    val contentPadding = theme.getItem("App::ContentPadding").asDouble()
    val borderColor = theme.getItem("Box::${colorScheme}/Border").asColor().kt()

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = (contentPadding / 2).dp)
        .padding(horizontal = contentPadding.dp)) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(5.dp))
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                content()
                Spacer(modifier = Modifier.height((contentPadding / 2).dp))
            }
        }
    }
}

@Preview
@Composable
fun MlBoxPreview(){
    val theme = DynamicTheme.getCurrentTheme()
    val contentPadding = theme.getItem("App::ContentPadding").asDouble()

    MlBox(MlBoxColorScheme.Danger) {
        MlLabel(text = "Hello World!")
        MlLabel(text = "Hello Compose!")
    }
}