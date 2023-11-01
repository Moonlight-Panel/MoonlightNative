package xyz.moonlightpanel.nativeapp.ui.components.display

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import xyz.moonlightpanel.nativeapp.ui.accessor.NavigationManager
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt

@Composable
fun MlLink(text: String, target: String, withPadding: Boolean = false){
    val theme = DynamicTheme.getCurrentTheme();
    val col = theme.getItem("Link::Color").asColor().kt()
    var px = theme.getItem("App::ContentPadding").asDouble()
    if(!withPadding)
        px = 0.0
    val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    val interactionSource = remember { MutableInteractionSource() }
    
    Box(modifier = Modifier
        .clickable(onClick = {
            uiScope.launch {
                NavigationManager.instance.showPage(target)
            }
        },
            interactionSource = interactionSource,
            indication = null
        )
        .padding(horizontal = px.dp)
        .padding(top = (px / 2).dp)) {
        Text(text = text, color = col)
    }
}

@Preview
@Composable
fun MlLinkPreview(){
    MlLink(text = "Hello World", target = "/Dashboard")
}