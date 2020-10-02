package cat.oscarromero.data.dto.moviedetails


import com.google.gson.annotations.SerializedName

data class VideosDto(
    @SerializedName("results")
    val results: List<VideoDto>
)
