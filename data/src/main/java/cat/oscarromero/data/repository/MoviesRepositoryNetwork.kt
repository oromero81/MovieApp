package cat.oscarromero.data.repository

import cat.oscarromero.data.*
import cat.oscarromero.data.adapter.NetworkResponse
import cat.oscarromero.domain.model.Genre
import cat.oscarromero.domain.model.Movie
import cat.oscarromero.domain.model.MovieDetails
import cat.oscarromero.domain.model.Video
import cat.oscarromero.domain.repository.MoviesRepository
import cat.oscarromero.domain.usecase.Error
import cat.oscarromero.domain.usecase.Result
import java.text.SimpleDateFormat
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

    override suspend fun getMovieDetails(movieId: Int): Result<Error, MovieDetails> {
        val response = movieApi.getMovieDetails(
            movieId,
            BuildConfig.TMDB_API_KEY,
            Locale.getDefault().toLanguageTag(),
            APPEND_TO_RESPONSE_VIDEOS
        )

        return if (response is NetworkResponse.Success) {
            val dto = response.body
            with(dto) {
                Result.Success(
                    MovieDetails(
                        id,
                        title,
                        posterPath,
                        backdropPath,
                        genres.map { Genre(it.id, it.name) },
                        originalTitle,
                        overview,
                        SimpleDateFormat(API_DATE_FORMAT).parse(releaseDate),
                        runtime,
                        videos.results
                            .filter { it.site == YOUTUBE }
                            .map { Video(it.id, Video.Site.YOUTUBE, it.type) },
                        voteAverage.toFloat()
                    )
                )
            }
        } else {
            handleGenericResponseError(response)
        }
    }
}
