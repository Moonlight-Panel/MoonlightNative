package xyz.moonlightpanel.nativeapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import xyz.moonlightpanel.nativeapp.ui.components.MlButton
import xyz.moonlightpanel.nativeapp.ui.components.MlButtonType
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.MoonlightNativeTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val theme = DynamicTheme.getCurrentTheme();
        val bg = theme.getItem("App::bg").asColor().kt();
        setContent {
            MoonlightNativeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().background(bg),
                    color = bg
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        MlButton("Hello Native!", MlButtonType.Primary, onClick = {
                            Log.d("BTN", "Clicked")
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoonlightNativeTheme {
        Greeting("Android")
    }
}