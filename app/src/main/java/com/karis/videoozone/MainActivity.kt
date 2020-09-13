package com.karis.videoozone

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.universalvideoview.UniversalMediaController
import com.universalvideoview.UniversalVideoView
import com.universalvideoview.UniversalVideoView.VideoViewCallback


class MainActivity : AppCompatActivity(), VideoViewCallback {
    var mVideoView: UniversalVideoView? = null
    var mMediaController: UniversalMediaController? = null
    var mBottomLayout: View? = null
    var mVideoLayout: View? = null
    var mStart: TextView? = null
    private var mSeekPosition = 0
    private var cachedHeight = 0
    private var isFullscreen = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mVideoLayout = findViewById(R.id.video_layout)
        mBottomLayout = findViewById(R.id.bottom_layout)
        mVideoView = findViewById<View>(R.id.videoView) as UniversalVideoView
        mMediaController = findViewById<View>(R.id.media_controller) as UniversalMediaController
        mVideoView!!.setMediaController(mMediaController)
        setVideoAreaSize()

        mVideoView!!.setVideoViewCallback(this)

        mStart = findViewById<View>(R.id.start) as TextView

        mStart!!.setOnClickListener {
            if (mSeekPosition > 0) {
                mVideoView!!.seekTo(mSeekPosition)
            }
            mVideoView!!.start()
            mMediaController!!.setTitle("brrrr")
        }
        mVideoView!!.setOnCompletionListener {
            Log.d(
                TAG,
                "onCompletion "
            )
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause ")
        if (mVideoView != null && mVideoView!!.isPlaying) {
            mSeekPosition = mVideoView!!.currentPosition
            Log.d(TAG, "onPause mSeekPosition=$mSeekPosition")
            mVideoView!!.pause()
        }
    }

    /**
     * 置视频区域大小
     */
    private fun setVideoAreaSize() {
        mVideoLayout!!.post {
            val width = mVideoLayout!!.width
            cachedHeight = (width * 405f / 720f).toInt()
            //                cachedHeight = (int) (width * 3f / 4f);
            //                cachedHeight = (int) (width * 9f / 16f);
            val videoLayoutParams = mVideoLayout!!.layoutParams
            videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            videoLayoutParams.height = cachedHeight
            mVideoLayout!!.layoutParams = videoLayoutParams
            mVideoView!!.setVideoPath(VIDEO_URL)
            mVideoView!!.requestFocus()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState Position=" + mVideoView!!.currentPosition)
        outState.putInt(SEEK_POSITION_KEY, mSeekPosition)
    }

    override fun onRestoreInstanceState(outState: Bundle) {
        super.onRestoreInstanceState(outState)
        mSeekPosition = outState.getInt(SEEK_POSITION_KEY)
        Log.d(
            TAG,
            "onRestoreInstanceState Position=$mSeekPosition"
        )
    }

    override fun onScaleChange(isFullscreen: Boolean) {
        this.isFullscreen = isFullscreen
        if (isFullscreen) {
            val layoutParams = mVideoLayout!!.layoutParams
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            mVideoLayout!!.layoutParams = layoutParams
            mBottomLayout!!.visibility = View.GONE
        } else {
            val layoutParams = mVideoLayout!!.layoutParams
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = cachedHeight
            mVideoLayout!!.layoutParams = layoutParams
            mBottomLayout!!.visibility = View.VISIBLE
        }
        switchTitleBar(!isFullscreen)
    }

    private fun switchTitleBar(show: Boolean) {
        val supportActionBar: ActionBar? = supportActionBar
        if (supportActionBar != null) {
            if (show) {
                supportActionBar.show()
            } else {
                supportActionBar.hide()
            }
        }
    }

    override fun onPause(mediaPlayer: MediaPlayer) {
        Log.d(TAG, "onPause UniversalVideoView callback")
    }

    override fun onStart(mediaPlayer: MediaPlayer) {
        Log.d(TAG, "onStart UniversalVideoView callback")
    }

    override fun onBufferingStart(mediaPlayer: MediaPlayer) {
        Log.d(TAG, "onBufferingStart UniversalVideoView callback")
    }

    override fun onBufferingEnd(mediaPlayer: MediaPlayer) {
        Log.d(TAG, "onBufferingEnd UniversalVideoView callback")
    }

    override fun onBackPressed() {
        if (isFullscreen) {
            mVideoView!!.setFullscreen(false)
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val SEEK_POSITION_KEY = "SEEK_POSITION_KEY"
        private const val VIDEO_URL = "https://www.youtube.com/watch?v=gzpvhVrIMG8dpm
        "
    }
}