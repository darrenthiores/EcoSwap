package com.darrenthiores.ecoswap.android.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.InsertChart
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.InsertChart
import androidx.compose.material.icons.rounded.Mail
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    Home(
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    Sustainability(
        selectedIcon = Icons.Rounded.InsertChart,
        unselectedIcon = Icons.Outlined.InsertChart
    ),
    Message(
        selectedIcon = Icons.Rounded.Mail,
        unselectedIcon = Icons.Rounded.MailOutline,
    ),
    Profile(
        selectedIcon = Icons.Default.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle
    );

    companion object{
        fun fromRoute(route: String?): TopLevelDestination? =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                Sustainability.name -> Sustainability
                Message.name -> Message
                Profile.name -> Profile
                else -> null
            }
    }
}