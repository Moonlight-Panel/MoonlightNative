package xyz.moonlightpanel.nativeapp.ui.components.interaction

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import xyz.moonlightpanel.nativeapp.ui.theme.DynamicTheme
import xyz.moonlightpanel.nativeapp.ui.theme.kt

@Composable
fun MlTextBox(placeholder: String, value: String = "", onValueChanged: (String) -> Unit = { }, showTextRenderer: (String) -> String = {it}) {
    val theme = DynamicTheme.getCurrentTheme()
    val paddingX = theme.getItem("TextBox::PaddingX").asDouble()
    val paddingY = theme.getItem("TextBox::PaddingY").asDouble()
    val topPaddingOverflow = theme.getItem("TextBox::TopPaddingOverflow").asDouble()
    val focusPlaceholderPadding = theme.getItem("TextBox::FocusPlaceholderPadding").asDouble()
    val borderColor = theme.getItem("TextBox::BorderColor").asColor().kt()
    val bg = theme.getItem("App::bg").asColor().kt()
    val placeholderColor = theme.getItem("TextBox::PlaceholderColor").asColor().kt()
    val textColor = theme.getItem("TextBox::TextColor").asColor().kt()

    var inEditing by remember {
        mutableStateOf(false)
    }
    var allowFocusLoss by remember {
        mutableStateOf(false)
    }

    var uValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = value
            )
        )
    }

    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = remember { FocusRequester() }
    val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(Dp.Unspecified)
                .padding(horizontal = paddingX.dp)
                .padding(bottom = paddingY.dp)
                .padding(top = (paddingY + topPaddingOverflow).dp)
                .clip(RoundedCornerShape(5.dp))
        ) {
            Box(
                modifier = Modifier
                    .size(Dp.Unspecified)
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            inEditing = true
                            Thread {
                                Thread.sleep(50)
                                uiScope.launch {
                                    focusRequester.freeFocus()
                                    focusRequester.requestFocus()
                                    allowFocusLoss = true
                                }
                                Thread
                                    .currentThread()
                                    .interrupt()
                            }.start()
                        },
                        interactionSource = interactionSource,
                        indication = rememberRipple(color = borderColor)
                    )
                    .border(1.dp, borderColor, shape = RoundedCornerShape(5.dp))
                    .clip(RoundedCornerShape(5.dp))
            ) {
                if (!inEditing) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = paddingX.dp)
                            .padding(vertical = paddingY.dp)
                    ) {
                        if (uValue.text == "") {
                            Text(text = placeholder, color = placeholderColor)
                        } else {
                            Text(text = showTextRenderer(uValue.text), color = textColor)
                        }
                    }
                } else {
                    BasicTextField(value = uValue,
                        onValueChange = {
                            uValue = it
                            onValueChanged(uValue.text)
                        },
                        modifier = Modifier
                            .background(Color.Transparent)
                            .fillMaxWidth()
                            .padding(horizontal = paddingX.dp)
                            .onFocusEvent {
                                if (!it.hasFocus && allowFocusLoss) {
                                    uiScope.launch {
                                        allowFocusLoss = false
                                        inEditing = false
                                    }
                                }
                            }
                            .focusRequester(focusRequester)
                            .padding(vertical = paddingY.dp),
                        singleLine = true,
                        keyboardActions = KeyboardActions(onDone = {
                            allowFocusLoss = false
                            inEditing = false
                        }),
                        cursorBrush = SolidColor(textColor),
                        textStyle = TextStyle.Default.copy(color = textColor)
                    )
                }
            }
        }


        if (inEditing) {
            Box(modifier = Modifier
                .padding(start = (2 * paddingX + 1).dp)) {
                Text(
                    text = placeholder,
                    color = placeholderColor,
                    modifier = Modifier.background(bg)
                        .padding(horizontal = focusPlaceholderPadding.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MlTextBoxPreview(){
    val theme = DynamicTheme.getCurrentTheme()
    val bg = theme.getItem("App::bg").asColor().kt()
    Box(modifier = Modifier.background(bg)) {
        Column {
            MlTextBox(placeholder = "Some placeholder", value = "")
            MlTextBox(placeholder = "Some placeholder 2", value = "")
            MlButton(text = "Testy", type = MlButtonType.Info)
        }
    }
}

val passwordCensoring: (String) -> String = {
    var str = ""

    for (i in it.indices)
        str += "*"

    val result = str
    result
}