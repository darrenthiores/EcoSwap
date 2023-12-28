package com.darrenthiores.ecoswap.android.presentation.add_progress.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.model_components.challenge.SelectableChallengeList
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.Title2B
import com.darrenthiores.ecoswap.domain.carbon.model.Challenge
import com.darrenthiores.ecoswap.domain.core.utils.Dummy

@Composable
fun ChallengeSheet(
    modifier: Modifier = Modifier,
    challenges: List<Challenge>,
    onSelectChallenge: (Challenge) -> Unit,
    listState: LazyListState
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(56.dp)
                .height(4.dp)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(32.dp)
                )
                .clip(
                    RoundedCornerShape(32.dp)
                )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier
                .padding(
                    horizontal = 24.dp
                ),
            text = stringResource(id = R.string.select_challenge_title),
            style = Title2B
        )

        Spacer(modifier = Modifier.height(12.dp))

        SelectableChallengeList(
            items = challenges,
            onClick = onSelectChallenge,
            state = listState,
            padding = PaddingValues(
                bottom = 24.dp,
                top = 12.dp,
                start = 24.dp,
                end = 24.dp
            )
        )
    }
}

@Preview
@Composable
private fun ChallengeSheetPreview() {
    EcoSwapTheme {
        val listState = rememberLazyListState()

        ChallengeSheet(
            challenges = Dummy.challenges,
            onSelectChallenge = {  },
            listState = listState
        )
    }
}