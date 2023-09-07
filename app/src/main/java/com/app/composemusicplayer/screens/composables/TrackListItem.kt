@file:OptIn(ExperimentalGlideComposeApi::class)

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.app.composemusicplayer.models.SongModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.dawinder.musicplayer_jetpackcompose.ui.theme.app_black
import com.dawinder.musicplayer_jetpackcompose.ui.theme.app_white
import com.dawinder.musicplayer_jetpackcompose.ui.theme.typography


@Composable
fun TrackListItem(track: SongModel, onTrackClick: () -> Unit) {
    val bgColor =  app_black
    val textColor = app_white
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(all = 5.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = bgColor)
            .clickable(onClick = onTrackClick)
    ) {
        GlideImage(
            model = track.cover.toString(),
            contentDescription = "",
            modifier = Modifier.padding(5.dp).size(60.dp).clip(CircleShape),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .weight(weight = 1f)
        ) {
            Text(text = track.name.toString(), style = typography.bodyLarge, color = textColor)
            Text(text = track.artist.toString(), style = typography.bodySmall, color = textColor)
        }

    }
}

