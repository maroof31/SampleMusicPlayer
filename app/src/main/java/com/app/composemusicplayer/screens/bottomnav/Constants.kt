package com.app.bottomtabscompose.ui.composables

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
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
