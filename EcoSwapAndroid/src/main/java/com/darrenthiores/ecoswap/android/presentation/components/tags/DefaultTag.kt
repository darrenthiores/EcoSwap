package com.darrenthiores.ecoswap.android.presentation.components.tags

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.theme.Caption2B
import com.darrenthiores.ecoswap.android.theme.Caption2R
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme

@Composable
fun DefaultTag(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit,
    selected: Boolean = false,
    disabled: Boolean = false,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    selectedIconColor: Color = MaterialTheme.colors.onSecondary,
    unselectedIconColor: Color = Color.Gray
) {
    val textFont = if (selected) Caption2B else Caption2R

    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = if (selected) MaterialTheme.colors.primary else Color.LightGray,
        contentColor = if (selected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onBackground,
        disabledBackgroundColor = Color.LightGray,
        disabledContentColor = Color.Gray
    )

    val buttonBorder = if (!selected) BorderStroke(
        width = 1.dp,
        color = Color.LightGray
    ) else null

    Button(
        modifier = modifier
            .defaultMinSize(
                minHeight = 25.dp
            )
            .height(24.dp),
        onClick = onClick,
        enabled = !disabled,
        shape = RoundedCornerShape(100.dp),
        border = buttonBorder,
        colors = buttonColors,
        contentPadding = PaddingValues(
            vertical = 0.dp,
            horizontal = 10.dp
        )
    ) {
        startIcon?.let {
            Icon(
                modifier = Modifier
                    .size(10.dp),
                imageVector = it,
                contentDescription = null,
                tint = if (selected) selectedIconColor else unselectedIconColor
            )

            Spacer(
                modifier = Modifier
                    .width(4.dp)
            )
        }

        Text(
            modifier = Modifier,
            text = label,
            style = textFont.copy(
                color = if (disabled) {
                    Color.Gray
                } else {
                    if (selected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onBackground
                }
            )
        )

        endIcon?.let {
            Spacer(
                modifier = Modifier
                    .width(4.dp)
            )

            Icon(
                modifier = Modifier
                    .size(10.dp),
                imageVector = it,
                contentDescription = null,
                tint = if (selected) selectedIconColor else unselectedIconColor
            )
        }
    }
}

@Preview(
    name = "Unselected Tag",
    showBackground = true
)
@Composable
private fun UnselectedTagPreview() {
    EcoSwapTheme {
        DefaultTag(
            label = "Test",
            startIcon = Icons.Default.Message,
            onClick = { }
        )
    }
}

@Preview(
    name = "Selected Tag",
    showBackground = true
)
@Composable
private fun SelectedTagPreview() {
    EcoSwapTheme {
        DefaultTag(
            label = "Test",
            onClick = { },
            selected = true
        )
    }
}

@Preview(
    name = "Disabled Tag",
    showBackground = true
)
@Composable
private fun DisabledTagPreview() {
    EcoSwapTheme {
        DefaultTag(
            label = "Test",
            onClick = { },
            disabled = true
        )
    }
}