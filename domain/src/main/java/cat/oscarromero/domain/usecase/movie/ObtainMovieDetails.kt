package cat.oscarromero.domain.usecase.movie

import cat.oscarromero.domain.model.MovieDetails
import cat.oscarromero.domain.repository.MoviesRepository
import cat.oscarromero.domain.usecase.Error
import cat.oscarromero.domain.usecase.Result
import cat.oscarromero.domain.usecase.UseCase
import javax.inject.Inject

class ObtainMovieDetails @Inject constructor(private val moviesRepository: MoviesRepository) :
    UseCase<MovieDetails, Int>() {

    override suspend fun run(params: Int): Result<Error, MovieDetails> =
        moviesRepository.getMovieDetails(params)
}
