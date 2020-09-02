package cat.oscarromero.domain.repository

import cat.oscarromero.domain.model.Movie
import cat.oscarromero.domain.usecase.Error
import cat.oscarromero.domain.usecase.Result

interface MoviesRepository {
    suspend fun getMovies(): Result<Error, List<Movie>>
}
