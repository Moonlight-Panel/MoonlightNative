package xyz.moonlightpanel.nativeapp.ui.pages.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.moonlightpanel.nativeapp.Delegate
import xyz.moonlightpanel.nativeapp.R
import xyz.moonlightpanel.nativeapp.lang.Langpack
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager
import xyz.moonlightpanel.nativeapp.ui.components.display.MlHeader
import xyz.moonlightpanel.nativeapp.ui.components.display.MlLabel
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButton
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButtonType
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.workflows.Workflow

@Composable
fun ConnectionErrorPage(){
    val lang = Langpack.INSTANCE.locale
    val theme = DynamicTheme.getCurrentTheme()
    val contentPadding = theme.getItem("App::ContentPadding").asDouble()

    Box(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())){
        Column {
            MlHeader(text = lang["connection.error"])
            MlLabel(text = lang["connection.error_hint"])
            Image(painter = painterResource(id = R.drawable.undraw_server_down_s_4_lk),
                contentDescription = "Image",
                Modifier.padding(contentPadding.dp))
            MlButton(text = lang["connection.reconnect"], type = MlButtonType.Primary, onClick = {
                Workflow.trigger(Workflow.STARTUP)
            })
        }
    }
}

fun ConnectionErrorPagePreloader(onFinish: Delegate){
    LayoutManager.hideNavigation()

    onFinish.invoke()
}

@Preview
@Composable
fun ConnectionErrorPagePreview(){
    ConnectionErrorPage()
}