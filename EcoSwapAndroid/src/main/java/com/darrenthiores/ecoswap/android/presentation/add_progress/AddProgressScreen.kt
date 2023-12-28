package com.darrenthiores.ecoswap.android.presentation.add_progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.add_progress.components.CarbonActivityDropDown
import com.darrenthiores.ecoswap.android.presentation.add_progress.components.CarbonCategoryDropDown
import com.darrenthiores.ecoswap.android.presentation.add_progress.components.ChallengeSheet
import com.darrenthiores.ecoswap.android.presentation.add_progress.components.ChallengeTaskDropDown
import com.darrenthiores.ecoswap.android.presentation.components.buttons.DefaultButton
import com.darrenthiores.ecoswap.android.presentation.components.buttons.SelectableButton
import com.darrenthiores.ecoswap.android.presentation.components.header.DefaultHeader
import com.darrenthiores.ecoswap.android.presentation.components.textfields.DefaultTextField
import com.darrenthiores.ecoswap.android.theme.SubHeadlineR
import com.darrenthiores.ecoswap.presentation.add_progress.AddProgressEvent
import com.darrenthiores.ecoswap.presentation.add_progress.AddProgressState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddProgressScreen(
    state: AddProgressState,
    onEvent: (AddProgressEvent) -> Unit,
    onBackClick: () -> Unit
) {
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val bottomScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scaffoldState = rememberScaffoldState()

    val coroutineScope = rememberCoroutineScope()

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
            onEvent(AddProgressEvent.LoadChallengeNextPage)
        }
    }

    BottomSheetScaffold(
        topBar = {
            DefaultHeader(
                modifier = Modifier
                    .padding(
                        horizontal = 24.dp,
                        vertical = 16.dp
                    ),
                text = stringResource(id = R.string.add_progress),
                onBackClick = onBackClick
            )
        },
        scaffoldState = bottomScaffoldState,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetBackgroundColor = MaterialTheme.colors.surface,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            ChallengeSheet(
                challenges = state.challenges.items,
                onSelectChallenge = {
                    onEvent(AddProgressEvent.OnSelectChallenge(it))

                    coroutineScope.launch {
                        bottomScaffoldState.bottomSheetState.collapse()
                    }
                },
                listState = challengeListState
            )
        }
    ) {
        Scaffold(
            floatingActionButton = {
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 24.dp
                        ),
                    label = stringResource(id = R.string.upload),
                    onClick = {
                        onEvent(AddProgressEvent.Upload)
                    },
                    disabled = state.category == null || (
                            state.activity == null && state.challenge == null && state.task == null
                            ) || state.isLoading,
                    isLoading = state.isLoading
                )
            },
            floatingActionButtonPosition = FabPosition.Center,
            scaffoldState = scaffoldState
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(
                    top = 16.dp,
                    bottom = 56.dp + 24.dp
                ),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    Text(
                        modifier = Modifier
                            .padding(
                                horizontal = 24.dp
                            ),
                        text = stringResource(id = R.string.category_add),
                        style = SubHeadlineR
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CarbonCategoryDropDown(
                        modifier = Modifier
                            .padding(
                                horizontal = 24.dp
                            ),
                        category = state.category,
                        isOpen = state.categoryDropDownOpen,
                        onClick = {
                            onEvent(AddProgressEvent.ToggleCategoryDropDown)
                        },
                        onDismiss = {
                            onEvent(AddProgressEvent.ToggleCategoryDropDown)
                        },
                        onSelectCategory = {
                            onEvent(AddProgressEvent.OnSelectCategory(it))
                        }
                    )
                }

                when(state.category?.id) {
                    "1" -> {
                        item {
                            Text(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 24.dp
                                    ),
                                text = stringResource(id = R.string.activity),
                                style = SubHeadlineR
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            CarbonActivityDropDown(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 24.dp
                                    ),
                                activities = state.activities,
                                activity = state.activity,
                                isOpen = state.activityDropDownOpen,
                                onClick = {
                                    onEvent(AddProgressEvent.ToggleActivityDropDown)
                                },
                                onDismiss = {
                                    onEvent(AddProgressEvent.ToggleActivityDropDown)
                                },
                                onSelectActivity = {
                                    onEvent(AddProgressEvent.OnSelectActivity(it))
                                }
                            )
                        }

                        item {
                            Text(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 24.dp
                                    ),
                                text = stringResource(id = R.string.meter),
                                style = SubHeadlineR
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            DefaultTextField(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 24.dp
                                    ),
                                text = state.number,
                                onTextChange = {
                                    onEvent(AddProgressEvent.OnNumberChange(it))
                                },
                                placeholder = stringResource(id = R.string.transport_placeholder),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Done,
                                    keyboardType = KeyboardType.NumberPassword
                                )
                            )
                        }
                    }
                    "2" -> {
                        item {
                            Text(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 24.dp
                                    ),
                                text = stringResource(id = R.string.activity),
                                style = SubHeadlineR
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            CarbonActivityDropDown(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 24.dp
                                    ),
                                activities = state.activities,
                                activity = state.activity,
                                isOpen = state.activityDropDownOpen,
                                onClick = {
                                    onEvent(AddProgressEvent.ToggleActivityDropDown)
                                },
                                onDismiss = {
                                    onEvent(AddProgressEvent.ToggleActivityDropDown)
                                },
                                onSelectActivity = {
                                    onEvent(AddProgressEvent.OnSelectActivity(it))
                                }
                            )
                        }

                        item {
                            Text(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 24.dp
                                    ),
                                text = stringResource(id = R.string.minute),
                                style = SubHeadlineR
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            DefaultTextField(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 24.dp
                                    ),
                                text = state.number,
                                onTextChange = {
                                    onEvent(AddProgressEvent.OnNumberChange(it))
                                },
                                placeholder = stringResource(id = R.string.energy_placeholder),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Done,
                                    keyboardType = KeyboardType.NumberPassword
                                )
                            )
                        }
                    }
                    "3" -> {
                        item {
                            Text(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 24.dp
                                    ),
                                text = stringResource(id = R.string.challenge),
                                style = SubHeadlineR
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            SelectableButton(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 24.dp
                                    ),
                                text = stringResource(id = R.string.select_challenge),
                                value = state.challenge?.title,
                                onClick = {
                                    coroutineScope.launch {
                                        bottomScaffoldState.bottomSheetState.expand()
                                    }
                                }
                            )
                        }

                        state.challenge?.let { challenge ->
                            item {
                                Text(
                                    modifier = Modifier
                                        .padding(
                                            horizontal = 24.dp
                                        ),
                                    text = stringResource(id = R.string.task),
                                    style = SubHeadlineR
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                ChallengeTaskDropDown(
                                    modifier = Modifier
                                        .padding(
                                            horizontal = 24.dp
                                        ),
                                    tasks = challenge.tasks,
                                    task = state.task,
                                    isOpen = state.taskDropDownOpen,
                                    onClick = {
                                        onEvent(AddProgressEvent.ToggleTaskDropDown)
                                    },
                                    onDismiss = {
                                        onEvent(AddProgressEvent.ToggleTaskDropDown)
                                    },
                                    onSelectTask = {
                                        onEvent(AddProgressEvent.OnSelectTask(it))
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}