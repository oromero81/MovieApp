package cat.oscarromero.movieapp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import cat.oscarromero.domain.model.Movie
import cat.oscarromero.domain.usecase.Result
import cat.oscarromero.domain.usecase.movie.ObtainListOfMovies
import cat.oscarromero.movieapp.THUMBNAIL_URL_BASE
import cat.oscarromero.movieapp.ui.model.MovieModel

class ListOfMoviesViewModel @ViewModelInject constructor(private val obtainListOfMovies: ObtainListOfMovies) :
    BaseViewModel() {

    val moviesLiveData = MutableLiveData<List<MovieModel>>()

    fun loadMovies() {
        obtainListOfMovies(Unit) {
            when (it) {
                is Result.Success -> handleListOfMovies(it.successData)
                is Result.Failure -> handleFailure(it.errorData)
            }
        }
    }

    private fun handleListOfMovies(movies: List<Movie>) {
        moviesLiveData.value =
            movies.map { MovieModel(it.id, it.name, "$THUMBNAIL_URL_BASE${it.thumbnail}") }
    }
}
