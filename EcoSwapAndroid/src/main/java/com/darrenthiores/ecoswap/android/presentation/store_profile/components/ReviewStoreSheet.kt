package com.darrenthiores.ecoswap.android.presentation.store_profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.buttons.DefaultButton
import com.darrenthiores.ecoswap.android.presentation.components.textfields.DefaultTextField
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.SubHeadlineR
import com.darrenthiores.ecoswap.android.theme.Title2B

@Composable
fun ReviewStoreSheet(
    modifier: Modifier = Modifier,
    rating: Int,
    message: String,
    isLoading: Boolean,
    onPickRating: (Int) -> Unit,
    onMessageChange: (String) -> Unit,
    onUpload: () -> Unit
) {
    val config = LocalConfiguration.current
    val width = config.screenWidthDp
    val starSize = (width-16-24)/6

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
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
            text = stringResource(id = R.string.review_store_header),
            style = Title2B
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = R.string.review_store_body),
            style = SubHeadlineR.copy(
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(5) { i ->
                IconButton(
                    modifier = Modifier
                        .padding(
                            horizontal = 6.dp
                        )
                        .then(
                            Modifier
                                .size(starSize.dp)
                        ),
                    onClick = {
                        onPickRating(i+1)
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(starSize.dp),
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "rating $i",
                        tint = if ((i+1) <= rating) MaterialTheme.colors.primary else Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        DefaultTextField(
            text = message,
            onTextChange = onMessageChange,
            placeholder = stringResource(id = R.string.review_placeholder),
            lineLimit = 5
        )

        Spacer(modifier = Modifier.weight(1f))

        DefaultButton(
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(id = R.string.add_review),
            onClick = onUpload,
            isLoading = isLoading,
            disabled = rating == 0 || message.isEmpty() || isLoading
        )
    }
}

@Preview
@Composable
private fun ReviewStoreSheetPreview() {
    EcoSwapTheme {
        ReviewStoreSheet(
            rating = 0,
            message = "awbgwaggawgbdwabduawbdawubadwhjbawdbhadwbhwadbhadwhbwadhbdwahbdbwahbdwawbgwaggawgbdwabduawbd",
            isLoading = false,
            onPickRating = {  },
            onMessageChange = {  },
            onUpload = {  }
        )
    }
}