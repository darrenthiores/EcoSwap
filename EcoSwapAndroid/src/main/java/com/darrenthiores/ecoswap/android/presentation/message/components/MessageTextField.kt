package com.darrenthiores.ecoswap.android.presentation.message.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.SubHeadlineR

@Composable
fun MessageTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit
) {
    BasicTextField(
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        modifier = modifier,
        textStyle = SubHeadlineR.copy(
            color = MaterialTheme.colors.onSurface
        ),
        maxLines = 3,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .background(
                        color = Color.LightGray.copy(
                            alpha = 0.3f
                        ),
                        shape = RoundedCornerShape(32.dp)
                    )
                    .padding(
                        vertical = 8.dp,
                        horizontal = 24.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.message_placeholder),
                            style = SubHeadlineR.copy(
                                color = Color.Gray
                            )
                        )
                    }

                    innerTextField()
                }
            }
        },
        cursorBrush = SolidColor(MaterialTheme.colors.primary)
    )
}

@Preview
@Composable
private fun MessageTextFieldPreview() {
    EcoSwapTheme(
        darkTheme = true
    ) {
        MessageTextField(
            text = "tes",
            onTextChange = {  }
        )
    }
}