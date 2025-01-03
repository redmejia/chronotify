package com.binarystack01.chronotify.components.textandtime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.binarystack01.chronotify.components.animation.brushLinearGradient
import com.binarystack01.chronotify.ui.theme.ChronotifyTheme
import java.time.LocalTime

// Container display text message and time
@Composable
fun TextAndTime(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    message: @Composable () -> Unit,
    time: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        title()
        message()
        time()
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TextAndTimePreview() {
    ChronotifyTheme(
        darkTheme = true
    ) {
        val currentTime = remember { mutableStateOf(LocalTime.now()) }
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val animatedBrush = brushLinearGradient()
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextAndTime(
                    title = {
                        TextDisplay(
                            text = "Hello",
                            style = TextStyle(
                                brush = animatedBrush,
                                fontSize = 40.sp,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    message = {
                        TextDisplay(
                            text = "Hello",
                            style = TextStyle(brush = animatedBrush),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                ) {
                    TextDisplay(
                        text = "${currentTime.value.hour}:" +
                                " ${currentTime.value.minute}:" +
                                " ${currentTime.value.second}",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(brush = animatedBrush),
                    )
                }
            }
        }
    }
}