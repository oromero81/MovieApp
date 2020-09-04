package cat.oscarromero.movieapp.ui.view.movidetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import cat.oscarromero.movieapp.R
import cat.oscarromero.movieapp.core.loadImageFromUrl
import cat.oscarromero.movieapp.ui.viewmodel.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.*

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()

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
        })

        val args: MovieDetailsFragmentArgs by navArgs()

        movieDetailsViewModel.loadMovie(args.movieId)
    }
}
