package cat.oscarromero.data.dto.videoinfo


import com.google.gson.annotations.SerializedName

data class InitRangeDto(
    @SerializedName("start")
    val start: String,
    @SerializedName("end")
    val end: String
)