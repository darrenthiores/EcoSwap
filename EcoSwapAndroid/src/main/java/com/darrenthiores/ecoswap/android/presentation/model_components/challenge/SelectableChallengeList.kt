package com.darrenthiores.ecoswap.android.presentation.model_components.challenge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.domain.carbon.model.Challenge

@Composable
fun SelectableChallengeList(
    modifier: Modifier = Modifier,
    items: List<Challenge>,
    onClick: (Challenge) -> Unit,
    state: LazyListState,
    padding: PaddingValues = PaddingValues(24.dp)
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = state,
        contentPadding = padding,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(
            items = items,
            key = { item -> item.id }
        ) { item ->
            SelectableChallengeItem(
                challenge = item,
                onClick = {
                    onClick(item)
                }
            )
        }
    }
}