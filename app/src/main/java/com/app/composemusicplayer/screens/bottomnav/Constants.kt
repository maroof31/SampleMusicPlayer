package com.app.bottomtabscompose.ui.composables

import com.app.composemusicplayer.R

object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "For You",
            icon = R.drawable.baseline_circle_24,
            route = "home"
        ),
        BottomNavItem(
            label = "Top Tracks",
            icon = R.drawable.baseline_circle_24,
            route = "search"
        )
    )
}
