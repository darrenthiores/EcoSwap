package com.darrenthiores.ecoswap.android.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.darrenthiores.ecoswap.android.theme.Caption2B
import com.darrenthiores.ecoswap.android.theme.Caption2R
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    onTabSelected: (TopLevelDestination) -> Unit,
) {
    val currentTopLevelDestination = TopLevelDestination.fromRoute(
        currentDestination?.route
    )

    BottomAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.primary
    ) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.primary,
            elevation = 0.dp
        ) {
            TopLevelDestination.values().forEach { destination ->
                BottomNavigationItem(
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = if (currentTopLevelDestination == destination) destination.selectedIcon else destination.unselectedIcon,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                            )

                            Text(
                                text = destination.name,
                                style = if (currentTopLevelDestination == destination) Caption2B else Caption2R
                            )
                        }
                    },
                    selected = currentTopLevelDestination == destination,
                    onClick = {
                        onTabSelected(destination)
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor =MaterialTheme.colors.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun AppBottomBarPreview() {
    EcoSwapTheme {
        AppBottomBar(
            currentDestination = null,
            onTabSelected = {  }
        )
    }
}