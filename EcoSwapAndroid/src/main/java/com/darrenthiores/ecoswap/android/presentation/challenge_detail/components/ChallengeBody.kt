package com.darrenthiores.ecoswap.android.presentation.challenge_detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.model_components.challenge.AccountImageRow
import com.darrenthiores.ecoswap.android.theme.CallOutB
import com.darrenthiores.ecoswap.android.theme.CallOutR
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.FootnoteR
import com.darrenthiores.ecoswap.domain.carbon.model.Challenge
import com.darrenthiores.ecoswap.domain.core.utils.Dummy

fun LazyListScope.challengeBody(
    challenge: Challenge?
) {
    item {
        Text(
            text = stringResource(id = R.string.description),
            style = CallOutB
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = challenge?.description?.ifEmpty { "-" } ?: "-",
            style = FootnoteR
        )

        Spacer(modifier = Modifier.height(16.dp))
    }

    item {
        Text(
            text = stringResource(id = R.string.details),
            style = CallOutB
        )

        Spacer(modifier = Modifier.height(4.dp))
    }
    
    items(
        items = challenge?.tasks ?: emptyList(),
        key = { task -> task.id }
    ) { task ->
        TaskItem(task = task)
    }

    item {
        Spacer(modifier = Modifier.height(16.dp))
    }

    item {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AccountImageRow(
                images = challenge
                    ?.participants
                    ?.take(3)
                    ?.map { it.imageUrl } ?: emptyList(),
                size = 38
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = challenge?.participants?.size.toString() + " ",
                style = CallOutB.copy(
                    color = MaterialTheme.colors.primary
                )
            )

            Text(
                text = stringResource(id = R.string.people_joined),
                style = CallOutR
            )
        }
    }
}

@Preview
@Composable
private fun ChallengeBodyPreview() {
    EcoSwapTheme {
        LazyColumn {
            challengeBody(
                challenge = Dummy.challenges[0]
            )
        }
    }
}