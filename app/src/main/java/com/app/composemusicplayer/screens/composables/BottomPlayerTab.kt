
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberImagePainter
import com.app.composemusicplayer.models.SongModel
import com.app.musicplayer.util.Constants
import com.dawinder.musicplayer_jetpackcompose.player.PlayerEvents
import com.dawinder.musicplayer_jetpackcompose.ui.composable.NextIcon
import com.dawinder.musicplayer_jetpackcompose.ui.composable.PlayPauseIcon
import com.dawinder.musicplayer_jetpackcompose.ui.composable.PreviousIcon
import com.dawinder.musicplayer_jetpackcompose.ui.composable.TrackImage
import com.dawinder.musicplayer_jetpackcompose.ui.composable.TrackName
import com.dawinder.musicplayer_jetpackcompose.ui.theme.app_black


@Composable
fun BottomPlayerTab(
    selectedTrack: SongModel, playerEvents: PlayerEvents, onBottomTabClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .background(color = app_black)
            .clickable(onClick = onBottomTabClick)
            .padding(all = 15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
        ) {
            val painter: Painter = rememberImagePainter(
                data = Constants.COVER_BASE_URL+selectedTrack.cover,
                builder = {

                }
            )
            TrackImage(
                trackImage = painter,
                modifier = Modifier.size(size = 40.dp)
                    .clip(CircleShape)
            )
            TrackName(trackName = selectedTrack.name.toString(), modifier = Modifier.weight(1f))
            PreviousIcon(onClick = playerEvents::onPreviousClick, isBottomTab = true)
            PlayPauseIcon(
                selectedTrack = selectedTrack,
                onClick = playerEvents::onPlayPauseClick,
                isBottomTab = true
            )
            NextIcon(onClick = playerEvents::onNextClick, isBottomTab = true)
        }
    }
}