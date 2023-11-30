package com.darrenthiores.ecoswap.android.presentation.store_item_detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.buttons.DefaultButton
import com.darrenthiores.ecoswap.android.presentation.store_item_detail.components.StoreItemDetailTopSection
import com.darrenthiores.ecoswap.android.presentation.store_item_detail.components.StoreItemInformationSection
import com.darrenthiores.ecoswap.android.presentation.store_item_detail.components.StoreProductDetailSection
import com.darrenthiores.ecoswap.presentation.store_item_detail.StoreItemDetailEvent
import com.darrenthiores.ecoswap.presentation.store_item_detail.StoreItemDetailState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StoreItemDetailScreen(
    state: StoreItemDetailState,
    onEvent: (StoreItemDetailEvent) -> Unit,
    onSettingClick: () -> Unit,
    onStoreClick: () -> Unit,
    onMessageClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val imagePagerState = rememberPagerState()
    
    Scaffold(
        bottomBar = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            ) {
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 24.dp,
                            vertical = 8.dp
                        ),
                    label = stringResource(id = R.string.message),
                    onClick = onMessageClick,
                    startIcon = Icons.Rounded.Message
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues),
            contentPadding = PaddingValues(
                bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { 
                StoreItemDetailTopSection(
                    images = state.item?.imgUrl ?: emptyList(),
                    pagerState = imagePagerState,
                    onSettingClick = onSettingClick,
                    onBackClick = onBackClick
                )
            }
            
            item { 
                StoreItemInformationSection(
                    item = state.item,
                    store = state.store,
                    isExpanded = state.isExpand,
                    onStoreClick = onStoreClick,
                    onToggleExpand = {
                        onEvent(StoreItemDetailEvent.ToggleExpand)
                    }
                )
            }
            
            item { 
                StoreProductDetailSection(item = state.item)
            }
        }
    }
}