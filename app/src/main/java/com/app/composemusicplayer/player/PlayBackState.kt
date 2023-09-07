package com.app.composemusicplayer.player

data class PlaybackState(
    val currentPlaybackPosition: Long,
    val currentTrackDuration: Long
)