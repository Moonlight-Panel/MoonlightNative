package xyz.moonlightpanel.nativeapp.ui.components.layout

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.moonlightpanel.nativeapp.R
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager
import xyz.moonlightpanel.nativeapp.ui.accessor.NavigationManager
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButton
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButtonType
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt


@Composable
fun BottomTabBar() {
    val theme = DynamicTheme.getCurrentTheme();
    val bg = theme.getItem("Navigation::Background").asColor().kt();
    val barHeight = theme.getItem("Navigation::Height").asDouble().toInt();

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(bg)
        .height(barHeight.dp))
    {
        Row {
            BottomBarButton(icon = R.drawable.bxs_dashboard, text = "Dashboard", modifier = Modifier.weight(1f), onClick = {
                NavigationManager.instance.showPage("/Dashboard")
            })
            BottomBarButton(icon = R.drawable.bx_store_alt, text = "Store", modifier = Modifier.weight(1f), onClick = {
                NavigationManager.instance.showPage("/Store")
            })
            BottomBarButton(icon = R.drawable.bxs_component, text = "Services", modifier = Modifier.weight(1f), onClick = {
                NavigationManager.instance.showPage("/Services")
            })
            BottomBarButton(icon = R.drawable.bx_group, text = "Community", modifier = Modifier.weight(1f), onClick = {
                NavigationManager.instance.showPage("/Community")
            })
            BottomBarButton(icon = R.drawable.bx_user_circle, text = "Me", modifier = Modifier.weight(1f), onClick = {
                NavigationManager.instance.showPage("/Account")
            })
        }
    }
}

@Preview
@Composable
fun BottomTabBarPreview() {
    BottomTabBar()
}