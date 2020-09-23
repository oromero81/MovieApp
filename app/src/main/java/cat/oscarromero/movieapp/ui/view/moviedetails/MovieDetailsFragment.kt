package cat.oscarromero.movieapp.ui.view.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import cat.oscarromero.movieapp.R
import cat.oscarromero.movieapp.core.loadImageFromUrl
import cat.oscarromero.movieapp.ui.viewmodel.MovieDetailsViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.*

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    private val videosAdapter = VideosAdapter {
        val action =
            MovieDetailsFragmentDirections.actionMovieDetailsFragmentToVideoPlayerFragment(it.videoId)
        findNavController().navigate(action)
    }
    private val creditsAdapter = CreditsAdapter {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDetailsViewModel.movieDetailsLiveData.observe(viewLifecycleOwner, Observer {
            backImageView.loadImageFromUrl(
                it.backdrop,
                R.drawable.ph_movie_details,
                R.drawable.ph_movie_details
            )
            voteTextView.text = it.vote
            titleTextView.text = it.name
            dateTextView.text = it.releaseDate
            durationTextView.text = it.duration
            descriptionTextView.text = it.description
            if (it.videos.isNotEmpty()) {
                videosAdapter.loadVideos(it.videos)
                videosRecyclerView.visibility = View.VISIBLE
            } else {
                videosRecyclerView.visibility = View.GONE
            }

            it.genres.forEach { genre ->
                val chip = Chip(requireContext())
                chip.text = genre.name
                genreChipGroup.addView(chip)
            }
            creditsAdapter.loadItems(it.credits)
        })

        val args: MovieDetailsFragmentArgs by navArgs()

        movieDetailsViewModel.loadMovie(args.movieId)
        videosRecyclerView.adapter = videosAdapter
        creditsRecyclerView.adapter = creditsAdapter
    }
}
