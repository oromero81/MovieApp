package cat.oscarromero.data.dto


import com.google.gson.annotations.SerializedName

data class VideosDto(
    @SerializedName("results")
    val results: List<VideoDto>
)
