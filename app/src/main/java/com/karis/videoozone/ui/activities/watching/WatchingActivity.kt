package com.karis.videoozone.ui.activities.watching

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karis.videoozone.R
import com.karis.videoozone.model.Videoitem
import com.karis.videoozone.util.Numberutils
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_watching.*
import org.joda.time.DateTime


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
        textView_numViews.text = Numberutils.getShortenedNumber(ytResponse?.statistics?.viewCount!!.toLong()) + " views"
        textView_numlikes.text = Numberutils.getShortenedNumber(ytResponse?.statistics?.likeCount!!.toLong()) + " likes"
        textView_numdislikes.text = Numberutils.getShortenedNumber(ytResponse?.statistics?.dislikeCount!!.toLong()) + " dislikes"
        textView_ReleaseDate.append( DateTime(ytResponse?.snippet?.publishedAt).toString(" MMM dd, yyyy hh:mm a"))

    }
}