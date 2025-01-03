package com.binarystack01.chronotify

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.binarystack01.chronotify.components.animation.brushLinearGradient
import com.binarystack01.chronotify.ui.theme.ChronotifyTheme
import kotlinx.coroutines.delay
import java.time.LocalTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChronotifyTheme(
                darkTheme = true
            ) {
                val animatedBrush = brushLinearGradient()

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Clock(
                        notifyMsg = { currentTime ->
                            Text(
                                buildAnnotatedString {
                                    withStyle(
                                        SpanStyle(
                                            brush = animatedBrush,
                                            fontSize = 40.sp
                                        )
                                    ) {
                                        append("kjk\n\n")
                                    }
                                    append("\n")
                                    withStyle(
                                        SpanStyle(
                                            brush = animatedBrush,
                                            fontSize = 30.sp
                                        )
                                    ) {
                                        append("Happy Learning\n\n")
                                        append("Happy Coding\n\n")
                                        append("and\n\n")
                                        append("Happy Hacking\n\n")
                                    }
                                    append("\n\n")
                                    withStyle(
                                        SpanStyle(
                                            fontSize = 30.sp
                                        )
                                    ) {
                                        append("\uD83C\uDF89 \uD83C\uDF8A")
                                    }
                                    append("\n\n")
                                    withStyle(
                                        SpanStyle(
                                            brush = animatedBrush,
                                            fontSize = 30.sp
                                        )
                                    ) {
                                        append("${currentTime.value.hour}: ${currentTime.value.minute}: ${currentTime.value.second}")
                                    }
                                },
                                textAlign = TextAlign.Center
                            )
                        },
                        timeNow = { currentTime ->
                            Text(buildAnnotatedString {
                                withStyle(
                                    SpanStyle(
                                        brush = animatedBrush,
                                        fontSize = 65.sp
                                    )
                                ) {
                                    append("Chronotify\n\n\n")
                                    append("${currentTime.value.hour}: ${currentTime.value.minute}: ${currentTime.value.second}")
                                }
                            }, textAlign = TextAlign.Center)
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Clock(
    notifyMsg: @Composable (MutableState<LocalTime>) -> Unit,
    timeNow: @Composable (MutableState<LocalTime>) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val newYearTime = LocalTime.of(0, 0)
            val currentTime = remember { mutableStateOf(LocalTime.now()) }

            val happyNewYear = remember { mutableStateOf(false) }

            LaunchedEffect(true) {
                while (true) {
                    currentTime.value = LocalTime.now()
                    if (currentTime.value.hour == newYearTime.hour
                        && currentTime.value.minute == newYearTime.minute
                    ) {
                        Log.d("ECHO", "PASS: ${currentTime.value}")
                        happyNewYear.value = true
                        // break
                    }
                    Log.d("ECHO", "COUNTING: ${currentTime.value}")
                    delay(1000)
                }
            }
            if (happyNewYear.value) {
                notifyMsg(currentTime)
            } else {
                timeNow(currentTime)
            }
        }
    }
}
