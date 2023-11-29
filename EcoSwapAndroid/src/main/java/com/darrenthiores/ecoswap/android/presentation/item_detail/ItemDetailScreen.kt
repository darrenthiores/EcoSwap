package com.darrenthiores.ecoswap.android.presentation.item_detail

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
import com.darrenthiores.ecoswap.android.presentation.item_detail.components.ItemDetailTopSection
import com.darrenthiores.ecoswap.android.presentation.item_detail.components.ItemInformationSection
import com.darrenthiores.ecoswap.android.presentation.item_detail.components.ProductDetailSection
import com.darrenthiores.ecoswap.presentation.item_detail.ItemDetailEvent
import com.darrenthiores.ecoswap.presentation.item_detail.ItemDetailState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemDetailScreen(
    state: ItemDetailState,
    onEvent: (ItemDetailEvent) -> Unit,
    onSettingClick: () -> Unit,
    onUserClick: () -> Unit,
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
                ItemDetailTopSection(
                    images = state.item?.imgUrl ?: emptyList(),
                    pagerState = imagePagerState,
                    onSettingClick = onSettingClick,
                    onBackClick = onBackClick
                )
            }
            
            item { 
                ItemInformationSection(
                    item = state.item,
                    user = state.user,
                    isExpanded = state.isExpand,
                    onUserClick = onUserClick,
                    onToggleExpand = {
                        onEvent(ItemDetailEvent.ToggleExpand)
                    }
                )
            }
            
            item { 
                ProductDetailSection(item = state.item)
            }
        }
    }
}