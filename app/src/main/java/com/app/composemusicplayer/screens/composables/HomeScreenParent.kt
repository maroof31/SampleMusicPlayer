@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)

package com.app.composemusicplayer.screens.composables

import BottomPlayerTab
import PlayerScreenPlay
import TrackListItem
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.composemusicplayer.models.SongModel
import com.app.composemusicplayer.player.PlaybackState
import com.app.composemusicplayer.player.PlayerEvents
import com.app.composemusicplayer.viewmodels.HomeViewModel
import com.dawinder.musicplayer_jetpackcompose.ui.theme.app_black
import com.dawinder.musicplayer_jetpackcompose.ui.theme.app_white
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
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        color = Color.Black,
    ) {     if(viewModel.songs.size==0){
          showLoading()
        }else {
        TrackList(
            tracks = viewModel.songs,
            selectedTrack = viewModel.selectedTrack,
            fullScreenState = fullScreenState,
            playerEvents = viewModel,
            playbackState = viewModel.playbackState,
            onBottomTabClick = onBottomTabClick,
            viewModel
        )
    }
    }
}

@Composable
fun TrackList(
    tracks: List<SongModel>,
    selectedTrack: SongModel?,
    fullScreenState: ModalBottomSheetState,
    playerEvents: PlayerEvents,
    playbackState: StateFlow<PlaybackState>,
    onBottomTabClick: () -> Unit,
    viewModel: HomeViewModel
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        ModalBottomSheetLayout(
            sheetContent = {
                if (selectedTrack != null) PlayerScreenPlay(
                    selectedTrack, playerEvents, playbackState,
                    viewModel
                )
            },
            sheetState = fullScreenState,
            sheetElevation = 8.dp,
        ) {
            Scaffold(Modifier.background(app_black)) { paddingValues ->
                Box(modifier = Modifier
                    .padding(top = paddingValues.calculateTopPadding())
                    .background(Color.Black)) {
                    Column {
                        LazyColumn(
                            modifier = Modifier
                                .weight(weight = 1f)
                                .background(app_black),
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

@Composable
fun showLoading(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(app_black), contentAlignment = Alignment.Center){
        CircularProgressIndicator(
            modifier = Modifier
                .size(size = 120.dp)
                .padding(all = 9.dp),
            color = app_white,
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Text(text = "Loading", fontSize = 16.sp, color = app_white)
    }
}