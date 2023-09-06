package com.dawinder.musicplayer_jetpackcompose.repositories

import Track
import com.app.composemusicplayer.models.SongModel

/**
 * An interface defining the operations for managing tracks.
 */
interface TrackRepository {

    /**
     * Retrieves a list of all tracks.
     *
     * @return a list of [Track] objects.
     */
    fun getTrackList(): List<Track>

    fun getSongsList():List<SongModel>
}
