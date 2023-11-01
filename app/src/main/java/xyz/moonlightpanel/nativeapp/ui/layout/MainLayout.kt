package xyz.moonlightpanel.nativeapp.ui.layout

import android.app.Activity
import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import xyz.moonlightpanel.nativeapp.Delegate
import xyz.moonlightpanel.nativeapp.DelegateT
import xyz.moonlightpanel.nativeapp.R
import xyz.moonlightpanel.nativeapp.ui.accessor.LayoutManager
import xyz.moonlightpanel.nativeapp.ui.accessor.NavigationManager
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButton
import xyz.moonlightpanel.nativeapp.ui.components.interaction.MlButtonType
import xyz.moonlightpanel.nativeapp.ui.components.layout.BottomTabBar
import xyz.moonlightpanel.nativeapp.ui.pages.AccountPage
import xyz.moonlightpanel.nativeapp.ui.pages.CommunityPage
import xyz.moonlightpanel.nativeapp.ui.pages.DashboardPage
import xyz.moonlightpanel.nativeapp.ui.pages.ServicesPage
import xyz.moonlightpanel.nativeapp.ui.pages.StorePage
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt

@Composable
fun MainLayout() {
    val theme = DynamicTheme.getCurrentTheme();
    val bg = theme.getItem("App::bg").asColor().kt()
    val barHeight = theme.getItem("Navigation::Height").asDouble()
    val loaderSize = theme.getItem("Navigation::LoaderSize").asDouble()
    val loaderDuration = theme.getItem("Navigation::LoaderDuration").asDouble()
    val loaderPadding = theme.getItem("Navigation::LoaderPadding").asDouble()
    val loaderColor = theme.getItem("Navigation::LoaderColor").asColor().kt()

    val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    var pd by remember {
        mutableIntStateOf(barHeight.toInt())
    }

    var dialogOpen by remember {
        mutableStateOf(false)
    }

    var loadingIndicator by remember {
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

    NavigationManager.instance.__putPage = DelegateT {
        uiScope.launch {
            page = it
        }
    }
    NavigationManager.instance.__putDialog = DelegateT {

        uiScope.launch {
            dialog = it
            dialogOpen = true
        }

    }
    NavigationManager.instance.__destroyDialog = Delegate {
        uiScope.launch {
            dialogOpen = false
        }
    }

    LayoutManager._showNav =
        Delegate {
            uiScope.launch {
                pd = barHeight.toInt()
            }
        }
    LayoutManager._hideNav = Delegate {
        uiScope.launch {
            pd = 0
        }
    }
    LayoutManager._setLoading = DelegateT {
        uiScope.launch {
            loadingIndicator = it
        }
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = bg.toArgb()
        }
    }

    NavigationManager.instance.finishInit()

    var rotationState by remember { mutableStateOf(0f) }

    val rotationTransition = rememberInfiniteTransition(label = "")
    val rotationAngle by rotationTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(loaderDuration.toInt(), easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

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

                if (loadingIndicator){
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(bg)){
                        Box(modifier = Modifier.padding(top = loaderPadding.dp).align(Alignment.TopCenter)){
                            Icon(
                                painter = painterResource(R.drawable.bx_loader_alt),
                                contentDescription = "Loader",
                                modifier = Modifier
                                    .size(loaderSize.dp)
                                    .rotate(rotationState + rotationAngle)
                                    .align(Alignment.TopCenter),
                                tint = loaderColor
                            )
                        }
                    }
                }else {
                    page()

                    if (dialogOpen) {
                        dialog()
                    }
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
    NavigationManager.instance.registerPage("/Account") { AccountPage() }
    NavigationManager.instance.registerPage("/Community") { CommunityPage() }
    NavigationManager.instance.registerPage("/Dashboard") { DashboardPage() }
    NavigationManager.instance.registerPage("/Services") { ServicesPage() }
    NavigationManager.instance.registerPage("/Store") { StorePage() }

    MainLayout()
}