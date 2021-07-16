package com.karis.videoozone.ui.fragments.watching

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.karis.videoozone.R
import com.karis.videoozone.databinding.FragmentWatchingBinding
import com.karis.videoozone.model.Videoitem
import com.karis.videoozone.model.YoutubeObject
import com.karis.videoozone.util.Numberutils
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.DateTime


@AndroidEntryPoint
class WatchingFragment : Fragment() {

    private lateinit var binding: FragmentWatchingBinding
    private val args: WatchingFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWatchingBinding.inflate(inflater, container, false)
        val ytResponse = args.youtube

        lifecycle.addObserver(binding.youtubePlayerView)

        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = ytResponse?.id
                youTubePlayer.loadVideo(videoId.toString(), 0f)
            }
        })

        binding.apply {

            videoDescription.text = ytResponse?.snippet?.description
            textViewVideoTitle.text = ytResponse?.snippet?.title
            textViewNumViews.text =
                Numberutils.getShortenedNumber(ytResponse?.statistics?.viewCount!!.toLong()) + " views"
            textViewNumlikes.text =
                Numberutils.getShortenedNumber(ytResponse?.statistics?.likeCount!!.toLong()) + " likes"
            textViewNumdislikes.text =
                Numberutils.getShortenedNumber(ytResponse?.statistics?.dislikeCount!!.toLong()) + " dislikes"
            textViewReleaseDate.append(DateTime(ytResponse?.snippet?.publishedAt).toString(" MMM dd, yyyy hh:mm a"))
            textViewReleaseDate.text = ytResponse?.snippet?.channelTitle

            shareIntent.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Yooooh! Checkout this awesome youtube Video https://www.youtube.com/watch?v=${ytResponse.id}"
                    )
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.youtubePlayerView.release()
    }

}