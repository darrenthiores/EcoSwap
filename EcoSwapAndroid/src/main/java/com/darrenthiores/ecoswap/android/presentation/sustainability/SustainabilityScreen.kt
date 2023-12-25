package com.darrenthiores.ecoswap.android.presentation.sustainability

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.model_components.challenge.ChallengeList
import com.darrenthiores.ecoswap.android.presentation.sustainability.components.SustainabilityTabRow
import com.darrenthiores.ecoswap.android.presentation.sustainability.components.SustainabilityTrackingContent
import com.darrenthiores.ecoswap.presentation.sustainability.SustainabilityEvent
import com.darrenthiores.ecoswap.presentation.sustainability.SustainabilityState
import com.darrenthiores.ecoswap.presentation.sustainability.model.SustainabilityTab

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SustainabilityScreen(
    state: SustainabilityState,
    onEvent: (SustainabilityEvent) -> Unit,
    onChallengeClick: (String) -> Unit,
    onAddClick: () -> Unit
) {
    val pagerState = rememberPagerState()
    val trackingListState = rememberLazyListState()
    val challengeListState = rememberLazyListState()

    val challengeReachEnd = remember(state.challenges.items) {
        derivedStateOf {
            challengeListState.layoutInfo
                .visibleItemsInfo
                .lastOrNull()?.index == state.challenges.items.size -1
        }
    }

    LaunchedEffect(challengeReachEnd.value) {
        if(challengeReachEnd.value && !state.challenges.endReached && !state.challenges.isLoading) {
            onEvent(SustainabilityEvent.LoadChallengeNextPage)
        }
    }

    Scaffold(
        topBar = {
            SustainabilityTabRow(
                pagerState = pagerState
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    modifier = Modifier
                        .size(32.dp),
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(id = R.string.add_carbon),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { contentPadding ->
        HorizontalPager(
            modifier = Modifier
                .padding(contentPadding),
            pageCount = SustainabilityTab.values().size,
            state = pagerState
        ) { index ->
            when (index) {
                0 -> {
                    SustainabilityTrackingContent(
                        state = state,
                        onEvent = onEvent,
                        listState = trackingListState
                    )
                }
                1 -> {
                    when {
                        state.challenges.isLoading -> {

                        }
                        state.challenges.items.isEmpty() -> {

                        }
                        else -> {
                            ChallengeList(
                                items = state.challenges.items,
                                onClick = onChallengeClick,
                                state = challengeListState,
                                padding = PaddingValues(
                                    top = 24.dp,
                                    start = 24.dp,
                                    end = 24.dp,
                                    bottom = 24.dp + 56.dp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}