@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)

package com.dawinder.musicplayer_jetpackcompose.ui.composable

import BottomPlayerTab
import PlayerScreenPlay
import TrackListItem
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.composemusicplayer.models.SongModel
import com.dawinder.musicplayer_jetpackcompose.player.PlaybackState
import com.dawinder.musicplayer_jetpackcompose.player.PlayerEvents
import com.dawinder.musicplayer_jetpackcompose.viewmodels.HomeViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenParent(viewModel: HomeViewModel) {
    val fullScreenState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden, skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()
    val onBottomTabClick: () -> Unit = { scope.launch { fullScreenState.show() } }
    Surface(
        modifier = Modifier.fillMaxSize().background(Color.White),
        color = Color.Black,
    ) {
        TrackList(
            tracks = viewModel.songs,
            selectedTrack = viewModel.selectedTrack,
            fullScreenState = fullScreenState,
            playerEvents = viewModel,
            playbackState = viewModel.playbackState,
            onBottomTabClick = onBottomTabClick
        )
    }
}

@Composable
fun TrackList(
    tracks: List<SongModel>,
    selectedTrack: SongModel?,
    fullScreenState: ModalBottomSheetState,
    playerEvents: PlayerEvents,
    playbackState: StateFlow<PlaybackState>,
    onBottomTabClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        ModalBottomSheetLayout(
            sheetContent = {
                if (selectedTrack != null) PlayerScreenPlay(
                    selectedTrack, playerEvents, playbackState
                )
            },
            sheetState = fullScreenState,
            sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            sheetElevation = 8.dp
        ) {
            Scaffold() { paddingValues ->
                Box(modifier = Modifier.padding(top = paddingValues.calculateTopPadding()).background(Color.Black)) {
                    Column {
                        LazyColumn(
                            modifier = Modifier.weight(weight = 1f),
                            contentPadding = PaddingValues(5.dp)
                        ) {
                            items(tracks) {
                                TrackListItem(
                                    track = it,
                                    onTrackClick = { playerEvents.onTrackClick(it) })
                            }
                        }
                        AnimatedVisibility(
                            visible = selectedTrack != null,
                            enter = slideInVertically(initialOffsetY = { fullHeight -> fullHeight })
                        ) {
                            BottomPlayerTab(selectedTrack!!, playerEvents, onBottomTabClick)
                        }
                    }
                }
            }
        }
    }
}