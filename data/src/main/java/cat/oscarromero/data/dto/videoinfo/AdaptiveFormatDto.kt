package cat.oscarromero.data.dto.videoinfo


import com.google.gson.annotations.SerializedName

data class AdaptiveFormatDto(
    @SerializedName("itag")
    val itag: Int,
    @SerializedName("url")
    val url: String?,
    @SerializedName("mimeType")
    val mimeType: String,
    @SerializedName("bitrate")
    val bitrate: Int,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("initRange")
    val initRange: InitRangeDto,
    @SerializedName("indexRange")
    val indexRange: IndexRangeDto,
    @SerializedName("lastModified")
    val lastModified: String,
    @SerializedName("contentLength")
    val contentLength: String,
    @SerializedName("quality")
    val quality: String,
    @SerializedName("fps")
    val fps: Int,
    @SerializedName("qualityLabel")
    val qualityLabel: String,
    @SerializedName("projectionType")
    val projectionType: String,
    @SerializedName("averageBitrate")
    val averageBitrate: Int,
    @SerializedName("approxDurationMs")
    val approxDurationMs: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("highReplication")
    val highReplication: Boolean,
    @SerializedName("audioQuality")
    val audioQuality: String,
    @SerializedName("audioSampleRate")
    val audioSampleRate: String,
    @SerializedName("audioChannels")
    val audioChannels: Int
)