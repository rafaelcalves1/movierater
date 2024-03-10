package com.projects.movierater.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.projects.movierater.R

sealed class Routes(
    val route: String,
    @StringRes val resourceStringId: Int,
    val icon: ImageVector
) {

    object Home : Routes(
        route = "Home",
        resourceStringId = R.string.route_home,
        icon = Icons.Outlined.Home
    )

}