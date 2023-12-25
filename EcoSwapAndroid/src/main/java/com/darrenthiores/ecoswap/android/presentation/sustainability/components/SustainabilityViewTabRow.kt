package com.darrenthiores.ecoswap.android.presentation.sustainability.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.FootnoteR
import com.darrenthiores.ecoswap.domain.carbon.model.CarbonView
import kotlinx.coroutines.launch

@Composable
fun SustainabilityViewTabRow(
    modifier: Modifier = Modifier,
    viewMode: CarbonView,
    onClick: (Int) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        modifier = modifier,
        selectedTabIndex = viewMode.ordinal,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.primary,
        divider = {  }
    ) {
        CarbonView.values().forEachIndexed { index, screen ->
            Tab(
                modifier = Modifier,
                text = {
                    Text(
                        text = screen.name,
                        style = FootnoteR
                    )
                },
                selected = viewMode.ordinal == index,
                onClick = {
                    coroutineScope.launch {
                        onClick(index)
                    }
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Preview
@Composable
private fun SustainabilityViewTabRowPreview() {
    EcoSwapTheme {
        SustainabilityViewTabRow(
            viewMode = CarbonView.Daily,
            onClick = {  }
        )
    }
}