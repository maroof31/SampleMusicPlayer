
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.app.composemusicplayer.models.SongModel
import com.app.composemusicplayer.player.PlaybackState
import com.app.composemusicplayer.player.PlayerEvents
import com.app.composemusicplayer.screens.composables.NextIcon
import com.app.composemusicplayer.screens.composables.PlayPauseIcon
import com.app.composemusicplayer.screens.composables.PreviousIcon
import com.app.composemusicplayer.viewmodels.HomeViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.dawinder.musicplayer_jetpackcompose.ui.theme.app_white
import com.dawinder.musicplayer_jetpackcompose.ui.theme.typography
import com.dawinder.musicplayer_jetpackcompose.utils.formatTime
import com.dawinder.musicplayer_jetpackcompose.utils.getCircularIndices

import kotlinx.coroutines.flow.StateFlow
import java.lang.Math.abs


@Composable
fun PlayerScreenPlay(
    selectedTrack: SongModel,
    playerEvents: PlayerEvents,
    playbackState: StateFlow<PlaybackState>,
    viewModel: HomeViewModel
) {
    val backgroundColor = Color(android.graphics.Color.parseColor(selectedTrack.accent))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)

    ) {
        Spacer(modifier = Modifier
            .height(100.dp)
            .background(backgroundColor))
        TrackInfoNew(
            trackImage = selectedTrack.cover.toString(),
            trackName = selectedTrack.name.toString(),
            artistName = selectedTrack.artist.toString(),
            viewModel,
            playerEvents
        )
        TrackProgressSlider(playbackState = playbackState) {
            playerEvents.onSeekBarPositionChanged(it)
        }
        
        TrackControls(
            selectedTrack = selectedTrack,
            onPreviousClick = playerEvents::onPreviousClick,
            onPlayPauseClick = playerEvents::onPlayPauseClick,
            onNextClick = playerEvents::onNextClick
        )
        Spacer(modifier = Modifier.height(50.dp))
    }
}


@Composable
fun TrackProgressSlider(
    playbackState: StateFlow<PlaybackState>,
    onSeekBarPositionChanged: (Long) -> Unit
) {
    val playbackStateValue = playbackState.collectAsState(
        initial = PlaybackState(0L, 0L)
    ).value
    var currentMediaProgress = playbackStateValue.currentPlaybackPosition.toFloat()
    var currentPosTemp by rememberSaveable { mutableStateOf(0f) }

    Slider(
        value = if (currentPosTemp == 0f) currentMediaProgress else currentPosTemp,
        onValueChange = { currentPosTemp = it },
        onValueChangeFinished = {
            currentMediaProgress = currentPosTemp
            currentPosTemp = 0f
            onSeekBarPositionChanged(currentMediaProgress.toLong())
        },
        valueRange = 0f..playbackStateValue.currentTrackDuration.toFloat(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = SliderDefaults.colors(
            thumbColor = Color.White,
            activeTrackColor = Color.White,
            inactiveTrackColor = Color.Gray
    ))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = playbackStateValue.currentPlaybackPosition.formatTime(),
            style = typography.bodySmall,
            color = app_white
        )
        Text(
            text = playbackStateValue.currentTrackDuration.formatTime(),
            style = typography.bodySmall,
            color = app_white
        )
    }
}


@Composable
fun TrackControls(
    selectedTrack: SongModel,
    onPreviousClick: () -> Unit,
    onPlayPauseClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PreviousIcon(onClick = onPreviousClick, isBottomTab = false)
        PlayPauseIcon(
            selectedTrack = selectedTrack,
            onClick = onPlayPauseClick,
            isBottomTab = false
        )
        NextIcon(onClick = onNextClick, isBottomTab = false)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TrackInfoNew(
    trackImage: String,
    trackName: String,
    artistName: String,
    viewModel: HomeViewModel,
    playerEvents: PlayerEvents

) {
        Box(
            modifier = Modifier
                .height(320.dp)
                .fillMaxWidth()
                .pointerInput(Unit){
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        val(x,y)=dragAmount
                        when{
                            x>50->{
                                playerEvents.onPreviousClick()
                            }
                            x<-50->{
                                playerEvents.onNextClick()
                            }
                        }
                    }
                }

        ) {
            val (previousIndex, nextIndex) = getCircularIndices(viewModel.songs.size, viewModel.selectedTrackIndex)
            GlideImage(
                model = viewModel.songs.get(previousIndex).cover.toString(),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(20.dp)
                    .align(Alignment.TopStart),
                contentScale = ContentScale.FillHeight
            )
            GlideImage(
                model = trackImage,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(280.dp)
                    .align(Alignment.TopCenter),
                contentScale = ContentScale.FillBounds
            )
            GlideImage(
                model =  viewModel.songs.get(nextIndex).cover.toString(),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(20.dp)
                    .align(Alignment.TopEnd),
                contentScale = ContentScale.FillHeight
            )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = trackName,
            style = typography.bodyLarge,
            color = app_white
        )

        Text(
            text = artistName,
            style = typography.bodySmall,
            color = app_white
        )
    }
}





