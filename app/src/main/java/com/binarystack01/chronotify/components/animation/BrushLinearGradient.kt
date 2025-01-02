package com.binarystack01.chronotify.components.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun brushLinearGradient(): Brush {

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val animatedColor1 by infiniteTransition.animateColor(
        initialValue = Color.White,
        targetValue = Color.Cyan,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        ), label = "Color one"
    )

    val animatedColor2 by infiniteTransition.animateColor(
        initialValue = Color.Cyan,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        ), label = "Color two"
    )

    val animatedColor3 by infiniteTransition.animateColor(
        initialValue = Color.Magenta,
        targetValue = Color.White,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        ), label = "Color three"
    )

    return Brush.linearGradient(
        colors = listOf(
            animatedColor1,
            animatedColor2,
            animatedColor3
        )
    )
}