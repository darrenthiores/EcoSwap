package com.darrenthiores.ecoswap.android.presentation.challenge_detail.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.header.SectionHeader
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.presentation.challenge_detail.ChallengeDetailEvent
import com.darrenthiores.ecoswap.presentation.challenge_detail.ChallengeDetailState

@Composable
fun ChallengeDetailContent(
    modifier: Modifier = Modifier,
    state: ChallengeDetailState,
    onEvent: (ChallengeDetailEvent) -> Unit,
    viewAll: Boolean = false,
    onToggleViewAll: () -> Unit,
    listState: LazyListState
) {
    val sidePadding = (-24).dp

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = listState,
        contentPadding = PaddingValues(
            start = 24.dp,
            end = 24.dp,
            top = 24.dp,
            bottom = 24.dp + 56.dp
        )
    ) {
        item {
            ChallengeCard(
                challenge = state.challenge,
                onJoinClick = {
                    onEvent(ChallengeDetailEvent.Join)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        challengeBody(
            challenge = state.challenge
        )

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Divider(
                modifier = Modifier
                    .layout { measurable, constraints ->
                        // Measure the composable adding the side padding*2 (left+right)
                        val placeable =
                            measurable.measure(constraints.offset(horizontal = -sidePadding.roundToPx() * 2))

                        //increase the width adding the side padding*2
                        layout(
                            placeable.width + sidePadding.roundToPx() * 2,
                            placeable.height
                        ) {
                            // Where the composable gets placed
                            placeable.place(+sidePadding.roundToPx(), 0)
                        }

                    },
                thickness = 3.dp
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            SectionHeader(
                title = stringResource(id = R.string.leaderboards),
                endAction = if (viewAll) stringResource(id = R.string.view_less) else stringResource(id = R.string.view_all),
                onActionClick = onToggleViewAll
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        itemsIndexed(
            items = state.challenge?.participants ?: emptyList(),
            key = { _, participant -> participant.id }
        ) { index, participant ->
            AccountLeaderboard(
                name = participant.name,
                imageUrl = participant.imageUrl,
                reduction = participant.progress,
                index = index
            )
        }
    }
}

@Preview
@Composable
private fun ChallengeDetailContentPreview() {
    EcoSwapTheme {
        val listState = rememberLazyListState()

        ChallengeDetailContent(
            state = ChallengeDetailState(),
            onEvent = {  },
            onToggleViewAll = {  },
            listState = listState
        )
    }
}