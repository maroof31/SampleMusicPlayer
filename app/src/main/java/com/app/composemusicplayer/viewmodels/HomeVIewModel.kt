@file:Suppress("EmptyMethod")

package com.dawinder.musicplayer_jetpackcompose.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import Track
import androidx.lifecycle.MutableLiveData
import com.app.composemusicplayer.models.SongModel
import com.app.composemusicplayer.repositories.Repository
import com.app.musicplayer.models.response.DataItem
import com.app.musicplayer.util.Constants
import com.dawinder.musicplayer_jetpackcompose.player.MyPlayer
import com.dawinder.musicplayer_jetpackcompose.player.PlaybackState
import com.dawinder.musicplayer_jetpackcompose.player.PlayerEvents
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_END
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_NEXT_TRACK
import com.dawinder.musicplayer_jetpackcompose.player.PlayerStates.STATE_PLAYING
import com.dawinder.musicplayer_jetpackcompose.repositories.TrackRepository
import com.dawinder.musicplayer_jetpackcompose.utils.collectPlayerState
import com.dawinder.musicplayer_jetpackcompose.utils.launchPlaybackStateJob
import com.dawinder.musicplayer_jetpackcompose.utils.resetTracks
import com.dawinder.musicplayer_jetpackcompose.utils.toMediaItemList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("EmptyMethod")
@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: Repository,
    trackRepository: TrackRepository,
    private val myPlayer: MyPlayer,
) : ViewModel(), PlayerEvents {


    private var _songsList: MutableLiveData<List<DataItem>?> = MutableLiveData()


    private val _songs = mutableStateListOf<SongModel>()

    val songs:List<SongModel> get()=_songs
    
    private var isTrackPlay: Boolean = false
    private var _isLoading = MutableLiveData<Boolean>()

    var selectedTrack: SongModel? by mutableStateOf(null)
        private set

    private var selectedTrackIndex: Int by mutableStateOf(-1)


    private var playbackStateJob: Job? = null


    private val _playbackState = MutableStateFlow(PlaybackState(0L, 0L))

    val playbackState: StateFlow<PlaybackState> get() = _playbackState


    private var isAuto: Boolean = false

    init {
        getsongsList()
        observePlayerState()
    }

    private fun onTrackSelected(index: Int) {
        if (selectedTrackIndex == -1) isTrackPlay = true
        if (selectedTrackIndex == -1 || selectedTrackIndex != index) {
            _songs.resetTracks()
            selectedTrackIndex = index
            setUpTrack()
        }
    }

    private fun setUpTrack() {
        if (!isAuto) myPlayer.setUpTrack(selectedTrackIndex, isTrackPlay)
        isAuto = false
    }

    private fun updateState(state: PlayerStates) {
        if (selectedTrackIndex != -1) {
            isTrackPlay = state == STATE_PLAYING
            _songs[selectedTrackIndex].state = state
            _songs[selectedTrackIndex].isSelected = true
            selectedTrack = null
            selectedTrack = songs[selectedTrackIndex]

            updatePlaybackState(state)
            if (state == STATE_NEXT_TRACK) {
                isAuto = true
                onNextClick()
            }
            if (state == STATE_END) onTrackSelected(0)
        }
    }

    fun getsongsList() {
        setIsLoading(true)
        viewModelScope.launch {
           val response = repository.getSongsL()
            response.body()?.data?.let { dataItems ->
                // Map DataItem objects to SongModel and add them to _songs list
                val songModels = dataItems.map { dataItem ->
                    SongModel(
                        dateUpdated = dataItem?.dateUpdated,
                        artist = dataItem?.artist,
                        dateCreated = dataItem?.dateCreated,
                        userCreated = dataItem?.userCreated,
                        sort = dataItem?.sort,
                        accent = dataItem?.accent,
                        url = dataItem?.url,
                        cover =Constants.COVER_BASE_URL+dataItem?.cover,
                        userUpdated = dataItem?.userUpdated,
                        topTrack = dataItem?.topTrack,
                        name = dataItem?.name,
                        id = dataItem?.id,
                        status = dataItem?.status,
                        state = PlayerStates.STATE_IDLE
                    )
                }
                _songs.addAll(songModels)
                myPlayer.iniPlayer(songs.toMediaItemList())
            }
        }
    }
    fun setIsLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }


    private fun observePlayerState() {
        viewModelScope.collectPlayerState(myPlayer, ::updateState)
    }

    private fun updatePlaybackState(state: PlayerStates) {
        playbackStateJob?.cancel()
        playbackStateJob = viewModelScope.launchPlaybackStateJob(_playbackState, state, myPlayer)
    }


    override fun onPreviousClick() {
        if (selectedTrackIndex > 0) onTrackSelected(selectedTrackIndex - 1)
    }


    override fun onNextClick() {
        if (selectedTrackIndex < songs.size - 1) onTrackSelected(selectedTrackIndex + 1)
    }


    override fun onPlayPauseClick() {
        myPlayer.playPause()
    }


    override fun onTrackClick(track: SongModel) {
        onTrackSelected(songs.indexOf(track))
    }

    override fun onSeekBarPositionChanged(position: Long) {
        viewModelScope.launch { myPlayer.seekToPosition(position) }
    }

    override fun onCleared() {
        super.onCleared()
        myPlayer.releasePlayer()
    }
}
