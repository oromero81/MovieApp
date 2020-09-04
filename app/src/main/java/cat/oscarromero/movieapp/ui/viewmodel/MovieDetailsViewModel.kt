package cat.oscarromero.movieapp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import cat.oscarromero.domain.model.MovieDetails
import cat.oscarromero.domain.usecase.Result
import cat.oscarromero.domain.usecase.movie.ObtainMovieDetails
import cat.oscarromero.movieapp.ui.model.MovieDetailsModel

class MovieDetailsViewModel @ViewModelInject constructor(private val obtainMovieDetails: ObtainMovieDetails) :
    BaseViewModel() {

    val movieDetailsLiveData = MutableLiveData<MovieDetailsModel>()

    fun loadMovie(movieId: Int) {
        obtainMovieDetails(movieId) {
            when (it) {
                is Result.Success -> handleMovieDetails(it.successData)
                is Result.Failure -> handleFailure(it.errorData)
            }
        }
    }

    private fun handleMovieDetails(movieDetails: MovieDetails) {
        movieDetailsLiveData.value = MovieDetailsModel.fromEntity(movieDetails)
    }
}
