package cat.oscarromero.movieapp.ui.model

import cat.oscarromero.domain.model.MovieDetails
import cat.oscarromero.movieapp.THUMBNAIL_URL_BASE
import java.text.SimpleDateFormat

data class MovieDetailsModel(
    val name: String,
    val backdrop: String,
    val genres: List<GenreModel>,
    val originalTitle: String,
    val description: String,
    val releaseDate: String,
    val duration: String,
    val vote: String
) {
    companion object {
        fun fromEntity(movieDetails: MovieDetails): MovieDetailsModel {
            return with(movieDetails) {
                MovieDetailsModel(
                    name,
                    "$THUMBNAIL_URL_BASE$backdrop",
                    genres.map { GenreModel(it.id, it.name) },
                    originalTitle,
                    description,
                    SimpleDateFormat("yyyy").format(releaseDate),
                    "$duration min",
                    vote.toString()
                )
            }
        }
    }
}
