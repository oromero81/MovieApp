package cat.oscarromero.data.dto.videoinfo


import com.google.gson.annotations.SerializedName

data class PlayabilityStatusDto(
    @SerializedName("status")
    val status: String,
    @SerializedName("messages")
    val messages: List<String>
)