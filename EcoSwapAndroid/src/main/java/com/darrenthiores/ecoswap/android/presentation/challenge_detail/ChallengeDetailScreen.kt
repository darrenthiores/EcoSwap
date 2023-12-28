package com.darrenthiores.ecoswap.android.presentation.challenge_detail

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.challenge_detail.components.ChallengeDetailContent
import com.darrenthiores.ecoswap.android.presentation.components.header.SettingHeader
import com.darrenthiores.ecoswap.presentation.challenge_detail.ChallengeDetailEvent
import com.darrenthiores.ecoswap.presentation.challenge_detail.ChallengeDetailState

@Composable
fun ChallengeDetailScreen(
    state: ChallengeDetailState,
    onEvent: (ChallengeDetailEvent) -> Unit,
    onAddClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val mainListState = rememberLazyListState()

    Scaffold(
        topBar = {
            SettingHeader(
                modifier = Modifier
                    .padding(16.dp),
                text = stringResource(id = R.string.challenge_details),
                onBackClick = onBackClick,
                onSettingClick = {  }
            )
        },
        floatingActionButton = {
            if (state.challenge?.isJoined == true) {
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
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { contentPadding ->
        ChallengeDetailContent(
            modifier = Modifier
                .padding(contentPadding),
            state = state,
            onEvent = onEvent,
            onToggleViewAll = {  },
            listState = mainListState
        )
    }
}