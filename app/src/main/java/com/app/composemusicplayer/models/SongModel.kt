package com.app.composemusicplayer.models

import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates

data class SongModel(
    val dateUpdated: String? = null,
    val artist: String? = null,
    val dateCreated: String? = null,
    val userCreated: String? = null,
    val sort: Any? = null,
    val accent: String? = null,
    val url: String? = null,
    val cover: String? = null,
    val userUpdated: String? = null,
    val topTrack: Boolean? = null,
    val name: String? = null,
    val id: Int? = null,
    val status: String? = null,
    var isSelected: Boolean = false,
    var state: PlayerStates = PlayerStates.STATE_IDLE
)