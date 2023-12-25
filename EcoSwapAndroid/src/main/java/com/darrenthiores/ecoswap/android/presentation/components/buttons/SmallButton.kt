package com.darrenthiores.ecoswap.android.presentation.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.theme.Caption2B
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.Gray1
import com.darrenthiores.ecoswap.android.theme.Gray2

@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    label: String?,
    onClick: () -> Unit,
    startIcon: ImageVector? = null,
    disabled: Boolean = false,
    color: Color = MaterialTheme.colors.primary,
    textColor: Color? = null,
    iconColor: Color? = null,
    type: ButtonType = ButtonType.Fill,
    isLoading: Boolean = false
) {
    val buttonHeight = 24.dp
    val textStyle = Caption2B
    val iconSize =20.dp
    val cornerRadius = 8.dp

    val bgColor = when(type) {
        ButtonType.Fill -> color
        is ButtonType.Outline -> type.background ?: MaterialTheme.colors.background
    }

    val contentColor = textColor ?: when(type) {
        ButtonType.Fill -> MaterialTheme.colors.onPrimary
        is ButtonType.Outline -> MaterialTheme.colors.onBackground
    }

    val disabledBgColor = when(type) {
        ButtonType.Fill -> Gray1
        is ButtonType.Outline -> MaterialTheme.colors.background
    }

    val disabledContentColor = when(type) {
        ButtonType.Fill -> MaterialTheme.colors.onPrimary
        is ButtonType.Outline -> Gray1
    }

    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = bgColor,
        contentColor = contentColor,
        disabledBackgroundColor = disabledBgColor,
        disabledContentColor = disabledContentColor
    )

    val buttonBorder = when(type) {
        is ButtonType.Outline -> BorderStroke(
            width = 1.dp,
            color = Gray1
        )
        else -> null
    }

    val spacing = 8.dp
    val paddingHorizontal = 16.dp
    val paddingVertical = 8.dp

    val elevation = null

    Button(
        modifier = modifier
            .defaultMinSize(
                minHeight = buttonHeight + paddingVertical
            )
            .height(buttonHeight + paddingVertical),
        onClick = onClick,
        enabled = !disabled,
        shape = RoundedCornerShape(cornerRadius),
        border = buttonBorder,
        colors = buttonColors,
        contentPadding = PaddingValues(
            vertical = 0.dp,
            horizontal = paddingHorizontal
        ),
        elevation = elevation
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(iconSize),
                color = contentColor
            )
        } else {
            startIcon?.let {
                Icon(
                    modifier = Modifier
                        .size(iconSize),
                    imageVector = it,
                    contentDescription = null,
                    tint = iconColor ?: (if (disabled) Gray2 else MaterialTheme.colors.onBackground)
                )

                label?.let {
                    Spacer(
                        modifier = Modifier
                            .width(spacing)
                    )
                }
            }

            label?.let {
                Text(
                    text = it,
                    style = textStyle,
                    modifier = Modifier
                        .padding(bottom = 4.dp) // stupid work around
                )
            }
        }
    }
}

@Preview(
    name = "Medium Rounded Fill Button"
)
@Composable
private fun MediumRoundedFillButtonPreview() {
    EcoSwapTheme {
        SmallButton(
            label = "Button",
            onClick = { }
        )
    }
}

@Preview(
    name = "Medium Pill Outline Button"
)
@Composable
private fun MediumPillOutlineButtonPreview() {
    EcoSwapTheme {
        SmallButton(
            label = "Button",
            onClick = { },
            type = ButtonType.Outline()
        )
    }
}
@Preview(
    name = "LoadingButton"
)
@Composable
private fun LoadingButtonPreview() {
    EcoSwapTheme {
        SmallButton(
            modifier = Modifier.fillMaxWidth(),
            label = "Button",
            onClick = { },
            type = ButtonType.Fill,
            isLoading = true
        )
    }
}