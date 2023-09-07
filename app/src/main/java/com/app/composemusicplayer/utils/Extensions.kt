package com.dawinder.musicplayer_jetpackcompose.utils

import androidx.media3.common.MediaItem
import com.app.composemusicplayer.models.SongModel
import com.app.composemusicplayer.player.MyPlayer
import com.app.composemusicplayer.player.*
import com.app.composemusicplayer.player.PlayerStates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


fun MutableList<SongModel>.resetTracks() {
    this.forEach { track ->
        track.isSelected = false
        track.state = PlayerStates.STATE_IDLE
    }
}


fun List<SongModel>.toMediaItemList(): MutableList<MediaItem> {
    return this.map { MediaItem.fromUri(it.url.toString()) }.toMutableList()
}

fun getCircularIndices(listSize: Int, currentIndex: Int): Pair<Int, Int> {
    val previousIndex = (currentIndex - 1 + listSize) % listSize
    val nextIndex = (currentIndex + 1) % listSize

    return Pair(previousIndex, nextIndex)
}

fun CoroutineScope.collectPlayerState(
    myPlayer: MyPlayer, updateState: (PlayerStates) -> Unit
) {
    this.launch {
        myPlayer.playerState.collect {
            updateState(it)
        }
    }
}

fun CoroutineScope.launchPlaybackStateJob(
    playbackStateFlow: MutableStateFlow<PlaybackState>, state: PlayerStates, myPlayer: MyPlayer
) = launch {
    do {
        playbackStateFlow.emit(
            PlaybackState(
                currentPlaybackPosition = myPlayer.currentPlaybackPosition,
                currentTrackDuration = myPlayer.currentTrackDuration
            )
        )
        delay(1000) // delay for 1 second
    } while (state == PlayerStates.STATE_PLAYING && isActive)
}

fun Long.formatTime(): String {
    val totalSeconds = this / 1000
    val minutes = totalSeconds / 60
    val remainingSeconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
}