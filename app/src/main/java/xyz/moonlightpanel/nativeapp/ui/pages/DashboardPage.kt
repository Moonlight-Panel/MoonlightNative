package xyz.moonlightpanel.nativeapp.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButton
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButtonType

@Composable
fun DashboardPage(){
    Box(modifier = Modifier.fillMaxSize()){
        Column {
            Text(text = "Dashboard")
            MlButton(text = "ShowLoader", type = MlButtonType.Primary, onClick = {
                LayoutManager.showLoadingIndicator()
            })
        }
    }
}

@Preview
@Composable
fun DashboardPagePreview(){
    DashboardPage()
}