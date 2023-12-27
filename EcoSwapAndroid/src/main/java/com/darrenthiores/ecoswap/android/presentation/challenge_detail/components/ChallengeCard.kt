package com.darrenthiores.ecoswap.android.presentation.challenge_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.bar.ProgressBarInfo
import com.darrenthiores.ecoswap.android.presentation.components.buttons.SmallButton
import com.darrenthiores.ecoswap.android.presentation.utils.ProgressColor
import com.darrenthiores.ecoswap.android.theme.CallOutB
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.FootnoteR
import com.darrenthiores.ecoswap.domain.carbon.model.Challenge
import com.darrenthiores.ecoswap.domain.core.utils.Dummy

@Composable
fun ChallengeCard(
    modifier: Modifier = Modifier,
    challenge: Challenge?,
    onJoinClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 1.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = challenge?.title ?: "",
                        style = CallOutB,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = stringResource(id = R.string.total_tasks, challenge?.tasks?.size ?: 0),
                        style = FootnoteR
                    )
                }

                ProgressBarInfo(
                    modifier = Modifier
                        .size(60.dp),
                    value = challenge?.progress ?: 0.0,
                    total = challenge?.goals ?: 0.0,
                    color = ProgressColor.Challenge.color
                )
            }

            if (challenge?.isJoined == false) {
                SmallButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = stringResource(id = R.string.join_challenge),
                    onClick = onJoinClick
                )
            }
        }
    }
}

@Preview
@Composable
private fun ChallengeCardPreview() {
    EcoSwapTheme {
        ChallengeCard(
            challenge = Dummy
                .challenges[0],
            onJoinClick = {  }
        )
    }
}

@Preview
@Composable
private fun JoinChallengeCardPreview() {
    EcoSwapTheme {
        val challenge = Dummy
            .challenges[0]
            .copy(
                isJoined = true
            )

        ChallengeCard(
            challenge = challenge,
            onJoinClick = {  }
        )
    }
}