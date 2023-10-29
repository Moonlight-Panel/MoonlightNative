package xyz.moonlightpanel.nativeapp.ui.components.interaction

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt

@Composable
fun MlButton(text: String, type: MlButtonType, onClick: () -> Unit = {}) {
    val theme = DynamicTheme.getCurrentTheme();
    val pfx = "Button::${type.name}/";
    val px = theme.getItem("Button::px").asDouble()
    val py = theme.getItem("Button::py").asDouble()
    val mx = theme.getItem("Button::mx").asDouble()
    val my = theme.getItem("Button::my").asDouble()
    val borderColor = theme.getItem("${pfx}Border").asColor().kt()
    val textColor = theme.getItem("Button::Text").asColor().kt()

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .size(Dp.Unspecified)
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(Color.Transparent)
            .padding(horizontal = mx.dp, vertical = my.dp)
    ){
        Box(modifier = Modifier
            .size(Dp.Unspecified)
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                interactionSource = interactionSource,
                indication = rememberRipple(color = borderColor)
            )
            .clip(RoundedCornerShape(5.dp))
            .background(Color.Transparent)
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(5.dp))
        ) {
            Text(
                text = text, modifier = Modifier
                    .padding(horizontal = px.dp, vertical = py.dp)
                    .align(Alignment.Center),
                color = textColor
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun MlButtonPreview() {
    MlButton(text = "Click Me!", type = MlButtonType.Primary)
}