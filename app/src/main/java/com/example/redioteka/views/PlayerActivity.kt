package com.example.redioteka.views

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.redioteka.databinding.ActivityPlayerBinding
import com.example.redioteka.viewmodels.MovieViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSource
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.upstream.HttpDataSource
import okhttp3.OkHttpClient

class PlayerActivity : AppCompatActivity() {
    protected var _binding: ActivityPlayerBinding? = null
    protected fun binding() = _binding!!

    protected val viewModel by viewModels<MovieViewModel>()

    private var player: ExoPlayer? = null
    private var videoPath: String? = null

    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding().root)

        viewModel.stream.observe(this) {
            videoPath = it.video
            playerInit()
        }
        viewModel.getStream(intent?.extras?.getString(
            MOVIE_ID
        ).toString())
    }

    public override fun onStart() {
        super.onStart()
        playerInit()
    }

    public override fun onResume() {
        super.onResume()
        hideSystemUi()
        if (player == null) {
            playerInit()
        }
    }

    public override fun onPause() {
        super.onPause()
    }

    public override fun onStop() {
        super.onStop()
        playerRelease()
    }

    private fun playerInit() {
        val factory: HttpDataSource.Factory = OkHttpDataSource.Factory(OkHttpClient.Builder().build())
        player = ExoPlayer.Builder(this)
            .setMediaSourceFactory(DefaultMediaSourceFactory(factory))
            .build()
            .also { exoPlayer ->
                binding().videoView.player = exoPlayer

                if (videoPath != null) {
                    videoPath = "http" + videoPath?.removePrefix("https")
                    Log.i("VIDEO", videoPath.toString())
                    val mediaItem = MediaItem.fromUri(videoPath!!)
                    exoPlayer.setMediaItem(mediaItem)
                    exoPlayer.playWhenReady = playWhenReady
                    exoPlayer.seekTo(currentWindow, playbackPosition)
                    exoPlayer.prepare()
                }
            }
    }

    private fun playerRelease() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentMediaItemIndex
            playWhenReady = this.playWhenReady
            release()
        }
        player = null
    }

    private fun hideSystemUi() {
        val ctrl = WindowInsetsControllerCompat(window, binding().videoView)
        ctrl.hide(WindowInsetsCompat.Type.systemBars());
    }

    companion object {
        const val MOVIE_ID = "0"
    }
}