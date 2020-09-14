package cat.oscarromero.data.dto.videoinfo


import com.google.gson.annotations.SerializedName

data class VideoInfoDto(
    @SerializedName("streamingData")
    val streamingData: StreamingDataDto?,
    @SerializedName("playabilityStatus")
    val playabilityStatus: PlayabilityStatusDto
)