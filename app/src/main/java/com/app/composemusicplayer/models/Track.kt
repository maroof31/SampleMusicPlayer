import com.app.composemusicplayer.player.PlayerStates

data class Track(
    val trackId: Int = 0,
    val trackName: String = "",
    val trackUrl: String = "",
    val trackImage: Int = 0,
    val artistName: String = "",
    var isSelected: Boolean = false,
    var state: PlayerStates = PlayerStates.STATE_IDLE
) {
        class Builder {
        private var trackId: Int = 0
        private lateinit var trackName: String
        private lateinit var trackUrl: String
        private var trackImage: Int = 0
        private lateinit var artistName: String
        private var isSelected: Boolean = false
        private var state: PlayerStates = PlayerStates.STATE_IDLE

        fun trackId(trackId: Int) = apply { this.trackId = trackId }
        fun trackName(trackName: String) = apply { this.trackName = trackName }
        fun trackUrl(trackUrl: String) = apply { this.trackUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3" }
        fun trackImage(trackImage: Int) = apply { this.trackImage = trackImage }
        fun artistName(artistName: String) = apply { this.artistName = artistName }

        fun build(): Track {
            return Track(
                trackId,
                trackName,
                trackUrl,
                trackImage,
                artistName,
                isSelected,
                state )
        }
    }
}
