
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.app.composemusicplayer.models.SongModel
import com.app.composemusicplayer.player.PlayerEvents
import com.app.composemusicplayer.screens.composables.PlayPauseIcon
import com.app.composemusicplayer.screens.composables.TrackName
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage



@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BottomPlayerTab(
    selectedTrack: SongModel, playerEvents: PlayerEvents, onBottomTabClick: () -> Unit
) {
    val backgroundColor = Color(android.graphics.Color.parseColor(selectedTrack.accent))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .clickable(onClick = onBottomTabClick)
            .padding(start = 15.dp,end=15.dp, bottom = 8.dp, top = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
        ) {
            GlideImage(
                model = selectedTrack.cover.toString(),
                contentDescription = "",
                modifier = Modifier
                    .padding(2.dp)
                    .size(45.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillBounds
            )
            TrackName(trackName = selectedTrack.name.toString(), modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(40.dp))
                        PlayPauseIcon(
                selectedTrack = selectedTrack,
                onClick = playerEvents::onPlayPauseClick,
                isBottomTab = true
            )
        }
    }
}