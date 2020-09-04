package cat.oscarromero.domain.model

import java.util.*

data class MovieDetails(
    val id: Int,
    val name: String,
    val poster: String,
    val backdrop: String,
    val genres: List<Genre>,
    val originalTitle: String,
    val description: String,
    val releaseDate: Date,
    val duration: Int,
    val videos: List<Video>,
    val vote: Float
)
