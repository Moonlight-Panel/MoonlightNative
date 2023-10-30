package xyz.moonlightpanel.nativeapp.ui.layout

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.moonlightpanel.nativeapp.Delegate
import xyz.moonlightpanel.nativeapp.DelegateT
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager
import xyz.moonlightpanel.nativeapp.ui.accessor.NavigationManager
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButton
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButtonType
import xyz.moonlightpanel.nativeapp.ui.components.layout.BottomTabBar
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt

@Composable
fun MainLayout() {
    val theme = DynamicTheme.getCurrentTheme();
    val bg = theme.getItem("App::bg").asColor().kt();
    val barHeight = theme.getItem("Navigation::Height").asDouble();

    var pd by remember {
        mutableIntStateOf(barHeight.toInt())
    }

    var dialogOpen by remember {
        mutableStateOf(false)
    }

    val pageDefault: @Composable () -> Unit = { Box(modifier = Modifier.fillMaxSize()) }
    val dialogDefault: @Composable () -> Unit = { Box(modifier = Modifier.fillMaxSize()) }

    var page by remember {
        mutableStateOf(pageDefault)
    }
    var dialog by remember {
        mutableStateOf(dialogDefault)
    }

    NavigationManager.instance.__putPage = DelegateT { t ->
        run {
            page = t
        }
    }
    NavigationManager.instance.__putDialog = DelegateT { t ->
        run {
            dialog = t;
            dialogOpen = true
        }
    }
    NavigationManager.instance.__destroyDialog = Delegate {
        dialogOpen = false
    }

    LayoutManager._showNav =
        Delegate { pd = barHeight.toInt() }
    LayoutManager._hideNav = Delegate { pd = 0 }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = bg.toArgb()
        }
    }

    NavigationManager.instance.finishInit()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(bg)
    ){
        Row(modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = pd.dp)) {

            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()){
                page()

                if (dialogOpen){
                    dialog()
                }
                /*Column {
                    MlButton(text = "Show Nav", type = MlButtonType.Info, onClick = {
                        LayoutManager.showNavigation()
                    })
                    MlButton(text = "Hide Nav", type = MlButtonType.Danger, onClick = {
                        LayoutManager.hideNavigation()
                    })
                }*/
            }
        }
        Row(modifier = Modifier
            .height(pd.dp)
            .align(Alignment.BottomCenter)) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
            ){
                BottomTabBar()
            }
        }
    }
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun MainLayoutPreview() {
    MainLayout()
}