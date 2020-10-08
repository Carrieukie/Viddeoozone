package com.karis.videoozone.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karis.videoozone.R
import com.karis.videoozone.model.Videoitem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_watching.*


@AndroidEntryPoint
class WatchingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watching)
        val ytResponse  = intent.extras?.getParcelable<Videoitem>("ytResponse")
        lifecycle.addObserver(youtube_player_view)
        youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = ytResponse?.id
                youTubePlayer.loadVideo(videoId.toString(), 0f)
            }
        })
        video_description.text = ytResponse?.snippet?.description
        textView_VideoTitle.text = ytResponse?.snippet?.title

    }
}