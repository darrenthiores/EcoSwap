package com.darrenthiores.ecoswap.android.presentation.sustainability.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.presentation.components.bar.TotalProgressBarInfo
import com.darrenthiores.ecoswap.android.presentation.utils.ProgressColor
import com.darrenthiores.ecoswap.domain.carbon.model.CarbonView
import com.darrenthiores.ecoswap.presentation.sustainability.SustainabilityEvent
import com.darrenthiores.ecoswap.presentation.sustainability.SustainabilityState

@Composable
fun SustainabilityTrackingContent(
    modifier: Modifier = Modifier,
    state: SustainabilityState,
    onEvent: (SustainabilityEvent) -> Unit,
    listState: LazyListState
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = listState,
        contentPadding = PaddingValues(
            top = 24.dp,
            start = 24.dp,
            end = 24.dp,
            bottom = 24.dp + 56.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TotalProgressBarInfo(
                values = state
                    .footPrint
                    ?.carbons
                    ?.map {
                        it.total
                    } ?: emptyList(),
                total = state.footPrint?.total ?: 0.0,
                colors = state
                    .footPrint
                    ?.carbons
                    ?.mapNotNull {
                        ProgressColor.getColorByCategoryId(
                            categoryId = it.categoryId
                        )?.color
                    } ?: emptyList()
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            SustainabilityViewTabRow(
                viewMode = state.viewMode,
                onClick = { index ->
                    onEvent(
                        SustainabilityEvent.OnViewModeClick(
                            mode = CarbonView.getByIndex(
                                index = index
                            )
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        items(
            items = state.footPrint?.carbons ?: emptyList(),
            key = { carbon -> carbon.categoryId }
        ) { carbon ->
            CarbonItem(
                carbon = carbon
            )
        }
    }
}