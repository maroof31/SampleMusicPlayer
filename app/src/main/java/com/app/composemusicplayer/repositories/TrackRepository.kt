package com.dawinder.musicplayer_jetpackcompose.repositories

import Track

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
}
