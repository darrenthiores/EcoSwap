package com.darrenthiores.ecoswap.android.presentation.add_item

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.add_item.components.ItemCategoryDropDown
import com.darrenthiores.ecoswap.android.presentation.add_item.components.ItemConditionDropDown
import com.darrenthiores.ecoswap.android.presentation.add_item.components.PhotoSection
import com.darrenthiores.ecoswap.android.presentation.components.buttons.DefaultButton
import com.darrenthiores.ecoswap.android.presentation.components.header.DefaultHeader
import com.darrenthiores.ecoswap.android.presentation.components.textfields.DefaultTextField
import com.darrenthiores.ecoswap.android.theme.SubHeadlineR
import com.darrenthiores.ecoswap.presentation.add_item.AddItemEvent
import com.darrenthiores.ecoswap.presentation.add_item.AddItemState

@Composable
fun AddItemScreen(
    state: AddItemState,
    photos: List<Uri>,
    onEvent: (AddItemEvent) -> Unit,
    onPickPhotos: (List<Uri>) -> Unit,
    onAddPhoto: (Uri) -> Unit,
    onSelectPhoto: (Uri) -> Unit,
    onUpload: () -> Unit,
    onBackClick: () -> Unit
) {
    val pickMultipleMedia = rememberLauncherForActivityResult(
        ActivityResultContracts.PickMultipleVisualMedia(
            maxItems =  5
        )
    ) { uris ->
        if (uris.size == 1) {
            val uri = uris.firstOrNull()
            uri?.let(onAddPhoto)
        } else {
            onPickPhotos(uris)
        }
    }

    Scaffold(
        topBar = {
            DefaultHeader(
                modifier = Modifier
                    .padding(
                        horizontal = 24.dp,
                        vertical = 16.dp
                    ),
                text = stringResource(id = R.string.add_item),
                onBackClick = onBackClick
            )
        },
        floatingActionButton = {
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 24.dp
                    ),
                label = stringResource(id = R.string.upload),
                onClick = onUpload,
                disabled = state.name.isEmpty() || state.description.isEmpty() ||
                state.category == null || state.total.isEmpty() || state.condition == null ||
                state.brand.isEmpty() || state.location.isEmpty() || state.isLoading,
                isLoading = state.isLoading
            )
        },
        floatingActionButtonPosition = FabPosition.Center
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
                    text = stringResource(id = R.string.add_photo),
                    style = SubHeadlineR
                )

                Spacer(modifier = Modifier.height(8.dp))

                PhotoSection(
                    photos = photos,
                    onAdd = {
                        pickMultipleMedia.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                    onSelect = onSelectPhoto
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    text = stringResource(id = R.string.add_name),
                    style = SubHeadlineR
                )

                Spacer(modifier = Modifier.height(8.dp))

                DefaultTextField(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    text = state.name,
                    onTextChange = {
                        onEvent(AddItemEvent.OnNameChange(it))
                    },
                    placeholder = stringResource(id = R.string.add_name_placeholder)
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    text = stringResource(id = R.string.add_desc),
                    style = SubHeadlineR
                )

                Spacer(modifier = Modifier.height(8.dp))

                DefaultTextField(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    text = state.description,
                    onTextChange = {
                        onEvent(AddItemEvent.OnDescriptionChange(it))
                    },
                    placeholder = stringResource(id = R.string.add_desc_placeholder),
                    lineLimit = 5
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    text = stringResource(id = R.string.add_category),
                    style = SubHeadlineR
                )

                Spacer(modifier = Modifier.height(8.dp))

                ItemCategoryDropDown(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    category = state.category,
                    isOpen = state.categoryDropDownOpen,
                    onClick = {
                        onEvent(AddItemEvent.ToggleCategoryDropDown)
                    },
                    onDismiss = {
                        onEvent(AddItemEvent.ToggleCategoryDropDown)
                    },
                    onSelectCategory = {
                        onEvent(AddItemEvent.OnSelectCategory(it))
                    }
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    text = stringResource(id = R.string.add_total),
                    style = SubHeadlineR
                )

                Spacer(modifier = Modifier.height(8.dp))

                DefaultTextField(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    text = state.total,
                    onTextChange = {
                        onEvent(AddItemEvent.OnTotalChange(it))
                    },
                    placeholder = stringResource(id = R.string.add_total),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Done
                    )
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    text = stringResource(id = R.string.add_condition),
                    style = SubHeadlineR
                )

                Spacer(modifier = Modifier.height(8.dp))

                ItemConditionDropDown(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    condition = state.condition,
                    isOpen = state.conditionDropDownOpen,
                    onClick = {
                        onEvent(AddItemEvent.ToggleConditionDropDown)
                    },
                    onDismiss = {
                        onEvent(AddItemEvent.ToggleConditionDropDown)
                    },
                    onSelectCondition = {
                        onEvent(AddItemEvent.OnSelectCondition(it))
                    }
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    text = stringResource(id = R.string.add_brand),
                    style = SubHeadlineR
                )

                Spacer(modifier = Modifier.height(8.dp))

                DefaultTextField(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    text = state.brand,
                    onTextChange = {
                        onEvent(AddItemEvent.OnBrandChange(it))
                    },
                    placeholder = stringResource(id = R.string.add_brand_placeholder)
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    text = stringResource(id = R.string.add_location),
                    style = SubHeadlineR
                )

                Spacer(modifier = Modifier.height(8.dp))

                DefaultTextField(
                    modifier = Modifier
                        .padding(
                            horizontal = 24.dp
                        ),
                    text = state.location,
                    onTextChange = {
                        onEvent(AddItemEvent.OnLocationChange(it))
                    },
                    placeholder = stringResource(id = R.string.add_location_placeholder),
                    lineLimit = 2
                )
            }
        }
    }
}