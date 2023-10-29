package xyz.moonlightpanel.nativeapp.ui.components.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.moonlightpanel.nativeapp.R
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt

@Composable
fun BottomBarButton(modifier: Modifier = Modifier, icon: Int, text: String, onClick: () -> Unit = {}){
    val theme = DynamicTheme.getCurrentTheme();
    val bg = theme.getItem("Navigation::Background").asColor().kt();
    val iconCol = theme.getItem("Navigation::IconColor").asColor().kt();
    val textCol = theme.getItem("Navigation::TextColor").asColor().kt();
    val clickCol = theme.getItem("Navigation::ClickColor").asColor().kt();

    val interactionSource = remember { MutableInteractionSource() }

    Box(modifier = modifier
        .fillMaxWidth()
        .height(72.dp).clickable(onClick = onClick,
            interactionSource = interactionSource,
            indication = rememberRipple(color = clickCol)
        )){

        Box(modifier = Modifier.align(Alignment.Center)) {
            Column() {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = text,
                    modifier = Modifier
                        .size(36.dp)
                        .align(Alignment.CenterHorizontally),
                    tint = iconCol
                )
                Text(text = text, color = textCol, modifier = Modifier.padding(top = (0).dp))
            }
        }
    }

}

@Composable
@Preview
fun BottomBarButtonPreview(){
    BottomBarButton(icon = R.drawable.bx_group, text = "Community")
}