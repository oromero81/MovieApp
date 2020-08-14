package cat.oscarromero.domain.usecase.movie

import cat.oscarromero.domain.model.Movie
import cat.oscarromero.domain.repository.MoviesRepository
import cat.oscarromero.domain.usecase.Error
import cat.oscarromero.domain.usecase.Result
import cat.oscarromero.domain.usecase.UseCase
import javax.inject.Inject

class ObtainListOfMovies @Inject constructor(private val moviesRepository: MoviesRepository) :
    UseCase<List<Movie>, Unit>() {

    override suspend fun run(params: Unit): Result<Error, List<Movie>> =
        moviesRepository.getMovies()
}
