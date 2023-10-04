package com.darrenthiores.ecoswap.android.presentation.boarding

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.boarding.component.BoardingSection
import com.darrenthiores.ecoswap.presentation.boarding.BoardingEvent
import com.darrenthiores.ecoswap.presentation.boarding.BoardingState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BoardingScreen(
    state: BoardingState,
    onEvent: (BoardingEvent) -> Unit
) {
    AnimatedContent(
        targetState = state.currentPage,
        modifier = Modifier
            .fillMaxSize(),
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = {
                    it
                }
            ) with slideOutHorizontally(
                targetOffsetX = {
                    -it
                }
            )
        },
        label = stringResource(id = R.string.boarding_label)
    ) { page ->
        when (page) {
            1 -> {
                BoardingSection(
                    page = state.currentPage,
                    imageId = R.drawable.boarding_1,
                    title = stringResource(id = R.string.boarding_title_1),
                    description = stringResource(id = R.string.dummy_text),
                    onNext = {
                        onEvent(
                            BoardingEvent.UpdateBoarding(page = 2)
                        )
                    },
                    onStart = {
                        onEvent(
                            BoardingEvent.Start
                        )
                    }
                )
            }
            2 -> {
                BoardingSection(
                    page = state.currentPage,
                    imageId = R.drawable.boarding_2,
                    title = stringResource(id = R.string.boarding_title_2),
                    description = stringResource(id = R.string.dummy_text),
                    onNext = {
                        onEvent(
                            BoardingEvent.UpdateBoarding(page = 3)
                        )
                    },
                    onStart = {
                        onEvent(
                            BoardingEvent.Start
                        )
                    }
                )
            }
            3 -> {
                BoardingSection(
                    page = state.currentPage,
                    imageId = R.drawable.boarding_3,
                    title = stringResource(id = R.string.boarding_title_3),
                    description = stringResource(id = R.string.dummy_text),
                    onNext = {  },
                    onStart = {
                        onEvent(
                            BoardingEvent.Start
                        )
                    }
                )
            }
        }
    }
}