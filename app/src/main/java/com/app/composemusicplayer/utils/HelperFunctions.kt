package com.app.musicplayer.util

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.app.musicplayer.models.response.DataItem

class HelperFunctions {

    companion object {

        fun madiaItems(songs: List<DataItem>): List<MediaItem> {
            val mediaItems: MutableList<MediaItem> = ArrayList()

            for (song in songs) {
                val mediaUri = Uri.parse(song.url)
                val mediaItem = MediaItem.Builder()
                    .setUri(mediaUri)
                    .setMediaMetadata(getMetadata(song))
                    .build()
                mediaItems.add(mediaItem)
            }
            return mediaItems
        }


        fun getMediaItem(song: DataItem): MediaItem {
            val mediaUri = Uri.parse(song.url)
            return MediaItem.Builder()
                .setUri(mediaUri)
                .setMediaMetadata(getMetadata(song))
                .build()
        }

        fun getMetadata(song: DataItem): MediaMetadata {
            val artworkUri = Uri.parse(Constants.COVER_BASE_URL + song.cover)
            return MediaMetadata.Builder()
                .setTitle(song.name)
                .setArtist(song.artist)
                .setArtworkUri(artworkUri)
                .build()
        }

    }
}