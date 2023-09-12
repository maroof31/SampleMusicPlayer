@file:OptIn(ExperimentalGlideComposeApi::class, ExperimentalGlideComposeApi::class)

package com.app.composemusicplayer.screens.composables


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.composemusicplayer.R
import com.app.composemusicplayer.models.SongModel
import com.app.composemusicplayer.player.PlayerStates
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.dawinder.musicplayer_jetpackcompose.ui.theme.app_white
import com.dawinder.musicplayer_jetpackcompose.ui.theme.md_theme_light_onPrimary
import com.dawinder.musicplayer_jetpackcompose.ui.theme.typography


@Composable
fun TrackImage(
    trackImage: Painter,
    modifier: Modifier
) {
    GlideImage(
        model = trackImage,
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(id = R.string.track_image),
        modifier = modifier.clip(shape = RoundedCornerShape(8.dp))
    )
}


@Composable
fun TrackName(trackName: String, modifier: Modifier) {
    Text(
        text = trackName,
        style = typography.bodyLarge,
        color = md_theme_light_onPrimary,
        modifier = modifier.padding(start = 16.dp, end = 8.dp)
    )
}
@Composable
fun PreviousIcon(onClick: () -> Unit, isBottomTab: Boolean) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.ic_previous),
            contentDescription = stringResource(id = R.string.icon_skip_previous),
            tint =  app_white,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun PlayPauseIcon(selectedTrack: SongModel, onClick: () -> Unit, isBottomTab: Boolean) {
    if (selectedTrack.state == PlayerStates.STATE_BUFFERING) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(size = 50.dp)
                .padding(all = 9.dp),
            color = app_white,
        )
    } else {
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(
                    id = if (selectedTrack.state == PlayerStates.STATE_PLAYING) R.drawable.baseline_pause_circle_filled_24
                    else R.drawable.ic_play),
                contentDescription = stringResource(id = R.string.icon_play_pause),
                tint = app_white,
                modifier = Modifier.size(70.dp)
            )
        }
    }
}
@Composable
fun NextIcon(onClick: () -> Unit, isBottomTab: Boolean) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = stringResource(id = R.string.icon_skip_next),
            tint = app_white,
            modifier = Modifier.size(30.dp)
        )
    }
}
