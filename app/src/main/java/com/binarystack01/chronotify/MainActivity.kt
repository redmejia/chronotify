package com.binarystack01.chronotify

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binarystack01.chronotify.components.animation.brushLinearGradient
import com.binarystack01.chronotify.components.textandtime.TextDisplay
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
                        header = {
                            TextDisplay(
                                text = "Chronotify",
                                style = TextStyle(
                                    textAlign = TextAlign.Center,
                                    brush = animatedBrush,
                                    fontSize = 65.sp
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        notifyMsg = {
                            TextDisplay(
                                text = "Learn, code and hack",
                                style = TextStyle(
                                    textAlign = TextAlign.Center,
                                    brush = animatedBrush,
                                    fontSize = 40.sp
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        timeNow = { currentTime ->
                            TextDisplay(
                                text = "${currentTime.value.hour}: " +
                                        "${currentTime.value.minute}:" +
                                        " ${currentTime.value.second}",
                                style = TextStyle(
                                    textAlign = TextAlign.Center,
                                    brush = animatedBrush,
                                    fontSize = 50.sp
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )

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
    header: @Composable () -> Unit,
    notifyMsg: @Composable () -> Unit,
    timeNow: @Composable (MutableState<LocalTime>) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            val selectedTime = LocalTime.of(0, 4)
            val currentTime = remember { mutableStateOf(LocalTime.now()) }

            val showMessage = remember { mutableStateOf(false) }

            LaunchedEffect(true) {
                while (true) {
                    currentTime.value = LocalTime.now()
                    if (currentTime.value.hour == selectedTime.hour
                        && currentTime.value.minute == selectedTime.minute
                    ) {
                        Log.d("ECHO", "PASS: ${currentTime.value}")
                        showMessage.value = true
                        // break
                    }
                    Log.d("ECHO", "COUNTING: ${currentTime.value}")
                    delay(1000)
                }
            }
            header()
            if (showMessage.value) {
                notifyMsg()
            }
            timeNow(currentTime)
            val animatedBrush = brushLinearGradient()
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                ),
                border = BorderStroke(width = 2.dp, brush = animatedBrush),
                onClick = {}
            )
            { Text("Create", fontSize = 20.sp, fontWeight = FontWeight.Medium) }
        }
    }
}
