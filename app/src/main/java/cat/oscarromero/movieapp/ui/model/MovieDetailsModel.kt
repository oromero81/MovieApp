package cat.oscarromero.movieapp.ui.model

import cat.oscarromero.domain.model.MovieDetails
import cat.oscarromero.movieapp.THUMBNAIL_CREDITS_URL_BASE
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
    val vote: String,
    val videos: List<VideoModel>,
    val credits: List<CreditModel>
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
                    vote.toString(),
                    videos.map { VideoModel(it.id, it.type) },
                    cast.map {
                        CreditModel(
                            it.id,
                            it.name,
                            it.job,
                            "$THUMBNAIL_CREDITS_URL_BASE${it.thumbnail}"
                        )
                    }
                )
            }
        }
    }
}
