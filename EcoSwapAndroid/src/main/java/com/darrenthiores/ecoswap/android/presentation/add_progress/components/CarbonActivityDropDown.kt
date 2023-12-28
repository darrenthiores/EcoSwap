package com.darrenthiores.ecoswap.android.presentation.add_progress.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.SubHeadlineR
import com.darrenthiores.ecoswap.domain.carbon.model.CarbonActivity
import com.darrenthiores.ecoswap.domain.core.utils.Constant

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CarbonActivityDropDown(
    modifier: Modifier = Modifier,
    activities: List<CarbonActivity>,
    activity: CarbonActivity?,
    isOpen: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onSelectActivity: (CarbonActivity) -> Unit
) {
    var rowSize by remember { mutableStateOf(Size.Zero) }

    Box(
        modifier = modifier
            .onGloballyPositioned { layoutCoordinates ->
                rowSize = layoutCoordinates.size.toSize()
            }
    ) {
        DropdownMenu(
            modifier = Modifier,
            expanded = isOpen,
            onDismissRequest = onDismiss
        ) {
            activities.forEachIndexed { index, activity ->
                DropdownMenuItem(
                    onClick = { onSelectActivity(activity) },
                    modifier = Modifier
                        .width(
                            with(LocalDensity.current) { rowSize.width.toDp() }
                        )
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = activity.display,
                        style = SubHeadlineR
                    )
                }

                if (index != activities.lastIndex) {
                    Divider()
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onClick,
            elevation = 5.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = activity?.display ?: stringResource(id = R.string.select_activity),
                    style = SubHeadlineR
                )

                Icon(
                    imageVector = if(isOpen) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = if(isOpen) stringResource(id = R.string.close) else stringResource(
                        id = R.string.open
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun CarbonActivityDropDownPreview() {
    EcoSwapTheme {
        CarbonActivityDropDown(
            activities = Constant.energyActivities,
            activity = null,
            isOpen = false,
            onClick = { },
            onDismiss = {  },
            onSelectActivity = {  }
        )
    }
}