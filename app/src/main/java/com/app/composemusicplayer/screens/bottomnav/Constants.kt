package com.app.bottomtabscompose.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search

object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "For You",
            icon = Icons.Filled.Search,
            route = "home"
        ),
        BottomNavItem(
            label = "Top Tracks",
            icon = Icons.Filled.Search,
            route = "search"
        )
    )
}
