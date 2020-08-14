package cat.oscarromero.data.repository

import cat.oscarromero.data.BuildConfig
import cat.oscarromero.data.MovieApi
import cat.oscarromero.data.adapter.NetworkResponse
import cat.oscarromero.data.handleGenericResponseError
import cat.oscarromero.domain.model.Movie
import cat.oscarromero.domain.repository.MoviesRepository
import cat.oscarromero.domain.usecase.Error
import cat.oscarromero.domain.usecase.Result
import java.util.*
import javax.inject.Inject

class MoviesRepositoryNetwork @Inject constructor(private val movieApi: MovieApi) :
    MoviesRepository {

    override suspend fun getMovies(): Result<Error, List<Movie>> {
        val response =
            movieApi.getPopularMovies(BuildConfig.TMDB_API_KEY, Locale.getDefault().toLanguageTag())

        return if (response is NetworkResponse.Success) {
            Result.Success(response.body.results.map { Movie(it.id, it.title, it.posterPath) })
        } else {
            handleGenericResponseError(response)
        }
    }
}
