package xyz.moonlightpanel.nativeapp.ui.layout

import android.app.Activity
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
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButton
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButtonType
import xyz.moonlightpanel.nativeapp.ui.components.layout.BottomTabBar
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt

@Composable
fun MainLayout() {
    var pd by remember {
        mutableIntStateOf(72)
    }

    LayoutManager._showNav = Delegate { pd = 72 }
    LayoutManager._hideNav = Delegate { pd = 0 }

    val theme = DynamicTheme.getCurrentTheme();
    val bg = theme.getItem("App::bg").asColor().kt();

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = bg.toArgb()
        }
    }

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

                Column {
                    MlButton(text = "Show Nav", type = MlButtonType.Info, onClick = {
                        LayoutManager.showNavigation()
                    })
                    MlButton(text = "Hide Nav", type = MlButtonType.Danger, onClick = {
                        LayoutManager.hideNavigation()
                    })
                }
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