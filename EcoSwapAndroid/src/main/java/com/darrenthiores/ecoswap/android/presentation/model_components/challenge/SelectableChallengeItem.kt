package com.darrenthiores.ecoswap.android.presentation.model_components.challenge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
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
import com.darrenthiores.ecoswap.android.presentation.utils.ProgressColor
import com.darrenthiores.ecoswap.android.theme.Caption1B
import com.darrenthiores.ecoswap.android.theme.Caption2B
import com.darrenthiores.ecoswap.android.theme.Caption2R
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.domain.carbon.model.Challenge
import com.darrenthiores.ecoswap.domain.core.utils.Dummy

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectableChallengeItem(
    modifier: Modifier = Modifier,
    challenge: Challenge,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = 1.dp,
        shape = RoundedCornerShape(10.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(1f),
                text = challenge.title,
                style = Caption1B,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Divider()

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.details),
                        style = Caption2B
                    )

                    Text(
                        text = challenge.description,
                        style = Caption2R,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AccountImageRow(
                            images = challenge
                                .participants
                                .take(3)
                                .map { it.imageUrl }
                        )
                        
                        Spacer(modifier = Modifier.width(4.dp))
                        
                        Text(
                            text = challenge.participants.size.toString() + " ",
                            style = Caption2B.copy(
                                color = MaterialTheme.colors.primary
                            )
                        )

                        Text(
                            text = stringResource(id = R.string.people_joined),
                            style = Caption2R
                        )
                    }
                }

                ProgressBarInfo(
                    modifier = Modifier
                        .size(60.dp),
                    value = challenge.progress,
                    total = challenge.goals,
                    color = ProgressColor.Challenge.color
                )
            }
        }
    }
}

@Preview
@Composable
private fun SelectableChallengeItemPreview() {
    EcoSwapTheme {
        SelectableChallengeItem(challenge = Dummy.challenges[0]) {
            
        }
    }
}