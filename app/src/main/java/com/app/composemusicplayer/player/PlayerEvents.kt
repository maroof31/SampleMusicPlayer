package com.dawinder.musicplayer_jetpackcompose.player

import Track
import com.app.composemusicplayer.models.SongModel


/**
 * An interface for handling player events such as play, pause, next, previous, and seek bar position changes.
 */
interface PlayerEvents {


    fun onPlayPauseClick()


    fun onPreviousClick()

    /**
     * Invoked when the next button is clicked.
     */
    fun onNextClick()

    /**
     * Invoked when a track is clicked. The clicked [Track] is provided as a parameter.
     *
     * @param track The track that was clicked.
     */
    fun onTrackClick(track: SongModel)

    /**
     * Invoked when the position of the seek bar has changed. The new position is provided as a parameter.
     *
     * @param position The new position of the seek bar.
     */
    fun onSeekBarPositionChanged(position: Long)
}
