package com.darrenthiores.ecoswap.android.presentation.components.bar

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.theme.Caption1B
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme

@Composable
fun TotalProgressBarInfo(
    modifier: Modifier = Modifier,
    values: List<Double>,
    total: Double,
    colors: List<Color>,
    strokeWidth: Dp = 56.dp
) {
    val background = MaterialTheme.colors.primary.copy(
        alpha = 0.2f
    )

    // adjust as category types
    val angleRatioTransport = remember {
        Animatable(0f)
    }
    val angleRatioEnergy = remember {
        Animatable(0f)
    }
    val angleRatioChallenge = remember {
        Animatable(0f)
    }

    LaunchedEffect(values) {
        angleRatioTransport.animateTo(
            targetValue = if(values[0]>0 && total>0) (values[0]/total).toFloat() else 0f,
            animationSpec = tween(
                durationMillis = 300
            )
        )

        angleRatioEnergy.animateTo(
            targetValue = if(values[1]>0 && total>0) (values[1]/total).toFloat() else 0f,
            animationSpec = tween(
                durationMillis = 300
            )
        )

        angleRatioChallenge.animateTo(
            targetValue = if(values[2]>0 && total>0) (values[2]/total).toFloat() else 0f,
            animationSpec = tween(
                durationMillis = 300
            )
        )
    }

    Box(
        modifier = modifier
            .padding(strokeWidth/2),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            drawArc(
                color = background,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                size = size,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )

            drawArc(
                color = colors[0],
                startAngle = 270f,
                sweepAngle = 360f * angleRatioTransport.value,
                useCenter = false,
                size = size,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Butt
                )
            )

            drawArc(
                color = colors[1],
                startAngle = 270f + (360f * angleRatioTransport.value),
                sweepAngle = 360f * angleRatioEnergy.value,
                useCenter = false,
                size = size,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Butt
                )
            )

            drawArc(
                color = colors[2],
                startAngle = 270f + (360f * angleRatioTransport.value) + (360f * angleRatioEnergy.value),
                sweepAngle = 360f * angleRatioChallenge.value,
                useCenter = false,
                size = size,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Butt
                )
            )
        }
    }
}

@Preview
@Composable
fun TotalProgressBarInfoPreview() {
    EcoSwapTheme {
        TotalProgressBarInfo(
            values = listOf(
                500.0,
                400.0,
                100.0
            ),
            total = 1000.0,
            colors = listOf(
                Color.Blue,
                Color.Green,
                Color.Red
            )
        )
    }
}

@Composable
fun ProgressBarInfo(
    modifier: Modifier = Modifier,
    value: Double,
    total: Double,
    color: Color,
    strokeWidth: Dp = 8.dp
) {
    val background = MaterialTheme.colors.primary.copy(
        alpha = 0.2f
    )
    val angleRatio = remember {
        Animatable(0f)
    }
    val progress: Int = ((value / total) * 100).toInt()

    LaunchedEffect(value) {
        angleRatio.animateTo(
            targetValue = if(value>0 && total>0) (value/total).toFloat() else 0f,
            animationSpec = tween(
                durationMillis = 300
            )
        )
    }

    Box(
        modifier = modifier
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            drawArc(
                color = background,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                size = size,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )

            drawArc(
                color = color,
                startAngle = 270f,
                sweepAngle = 360f * angleRatio.value,
                useCenter = false,
                size = size,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }

        Text(
            text = stringResource(id = R.string.progress, progress),
            style = Caption1B
        )
    }
}

@Preview
@Composable
private fun ProgressBarInfoPreview() {
    EcoSwapTheme {
        ProgressBarInfo(
            modifier = Modifier
                .size(60.dp),
            value = 100.0,
            total = 200.0,
            color = MaterialTheme.colors.secondary
        )
    }
}