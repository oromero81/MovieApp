package cat.oscarromero.data.dto.videoinfo


import com.google.gson.annotations.SerializedName

data class StreamingDataDto(
    @SerializedName("expiresInSeconds")
    val expiresInSeconds: String,
    @SerializedName("formats")
    val formats: List<FormatDto>,
    @SerializedName("adaptiveFormats")
    val adaptiveFormats: List<AdaptiveFormatDto>?,
    @SerializedName("dashManifestUrl")
    val dashManifestUrl: String?
)