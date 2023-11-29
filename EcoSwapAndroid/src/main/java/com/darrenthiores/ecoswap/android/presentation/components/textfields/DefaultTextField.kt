package com.darrenthiores.ecoswap.android.presentation.components.textfields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.SubHeadlineR

@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    background: Color = MaterialTheme.colors.background,
    disabled: Boolean = false,
    isError: Boolean = false,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    lineLimit: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Done
    ),
    onEndIconClick: () -> Unit = {  },
    onFocusChange: (Boolean) -> Unit = {  },
    onSubmit: () -> Unit = {  }
) {
    val focusManager = LocalFocusManager.current
    var isFocused by remember {
        mutableStateOf(false)
    }

    val textStyle = SubHeadlineR
    val iconSize = 20.dp
    val textFieldHeight = 48.dp
    val textHeight = 32.dp

    BasicTextField(
        value = text,
        onValueChange = {
            onTextChange(it)
        },
        modifier = modifier
            .onFocusChanged {
                isFocused = it.isFocused
                onFocusChange(it.isFocused)
            },
        textStyle = textStyle.copy(
            color = if (disabled) Color.LightGray else MaterialTheme.colors.onBackground
        ),
        maxLines = lineLimit,
        cursorBrush = SolidColor(MaterialTheme.colors.secondary),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .background(
                        color = if (disabled) Color.Gray else background,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = if (isError) Color.Red else {
                            if (isFocused) MaterialTheme.colors.secondary else Color.Gray
                        },
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 16.dp)
                    .height(
                        max(
                            a = textFieldHeight,
                            b = (textHeight.times(lineLimit))
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                startIcon?.let {
                    Icon(
                        modifier = Modifier
                            .size(iconSize),
                        imageVector = it,
                        contentDescription = null,
                        tint = if (disabled) Color.DarkGray else MaterialTheme.colors.primary
                    )

                    Spacer(
                        modifier = Modifier
                            .width(8.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Color.Gray,
                            style = textStyle,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    innerTextField()
                }

                if (text.isNotEmpty()) {
                    Spacer(
                        modifier = Modifier
                            .width(8.dp)
                    )

                    val clickableModifier = Modifier
                        .size(iconSize)
                        .clickable {
                            onTextChange("")
                        }
                    val disabledModifier = Modifier
                        .size(iconSize)
                    val endIconModifier = if (disabled) disabledModifier
                    else clickableModifier

                    Icon(
                        modifier = endIconModifier,
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = "Delete Text",
                        tint = Color.Gray
                    )
                } else {
                    endIcon?.let {
                        Spacer(
                            modifier = Modifier
                                .width(8.dp)
                        )

                        IconButton(
                            modifier = Modifier
                                .then(
                                    Modifier
                                        .size(iconSize)
                                ),
                            onClick = onEndIconClick
                        ) {
                            Icon(
                                modifier = Modifier,
                                imageVector = it,
                                contentDescription = null,
                                tint = if (disabled) Color.Gray else MaterialTheme.colors.primary
                            )
                        }
                    }
                }
            }
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                onSubmit()
            }
        ),
        enabled = !disabled
    )
}

@Preview
@Composable
private fun DefaultTextFieldPreview() {
    EcoSwapTheme {
        DefaultTextField(
            text = "",
            onTextChange = {  },
            placeholder = "Your name",
            background = Color.LightGray,
            startIcon = Icons.Rounded.MailOutline
        )
    }
}
