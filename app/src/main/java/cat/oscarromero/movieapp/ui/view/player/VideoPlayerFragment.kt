package cat.oscarromero.movieapp.ui.view.player

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import cat.oscarromero.movieapp.R
import cat.oscarromero.movieapp.ui.viewmodel.VideoPlayerViewModel
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_video_player.*


@AndroidEntryPoint
class VideoPlayerFragment : Fragment() {

    private val videoPlayerViewModel: VideoPlayerViewModel by viewModels()

    private val player by lazy {
        SimpleExoPlayer.Builder(requireContext()).build().apply {
            addListener(
                object : Player.EventListener {
                    override fun onPlayerError(error: ExoPlaybackException) {
                        showError(error.message ?: "")
                    }
                })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
    }

    override fun onResume() {
        super.onResume()
        enterFullscreen()
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerView.player = player

        videoPlayerViewModel.videoUrlLiveData.observe(viewLifecycleOwner, Observer {
            val mediaItem = MediaItem.fromUri(it)
            if (it.contains("dash")) {
                val mediaSource = DashMediaSource.Factory(DefaultHttpDataSourceFactory("MovieApp"))
                    .createMediaSource(mediaItem)
                player.setMediaSource(mediaSource)
            } else {
                player.setMediaItem(mediaItem)
            }
            player.prepare()
            player.play()

            playerView.visibility = View.VISIBLE
        })

        videoPlayerViewModel.failureLiveData.observe(viewLifecycleOwner, Observer {
            showError(it.errorMessage)
        })

        videoPlayerViewModel.loadingLiveData.observe(viewLifecycleOwner, Observer {
            loading.visibility = if (it) {
                View.VISIBLE
            } else {
                View.GONE
            }
        })

        if (savedInstanceState == null) {
            val args: VideoPlayerFragmentArgs by navArgs()
            videoPlayerViewModel.obtainVideoUrl(args.videoId)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        exitFullscreen()
    }

    private fun showError(message: String) {
        val builder: AlertDialog.Builder =
            AlertDialog.Builder(requireContext()).apply {
                setMessage(message)
                setPositiveButton("Close") { _, _ ->
                    Navigation.findNavController(requireView()).popBackStack()
                }
            }

        builder.create().show()
    }

    private fun enterFullscreen() {
        activity?.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    private fun exitFullscreen() {
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }
}
