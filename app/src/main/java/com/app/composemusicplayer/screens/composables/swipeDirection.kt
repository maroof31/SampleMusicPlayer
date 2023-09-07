package com.app.composemusicplayer.screens.composables

sealed class SwipeDirection {
    object Left : SwipeDirection()
    object Right : SwipeDirection()
}