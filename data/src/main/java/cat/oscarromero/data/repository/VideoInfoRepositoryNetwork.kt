package cat.oscarromero.data.repository

import cat.oscarromero.data.YOUTUBE_INFO_URL
import cat.oscarromero.data.YOUTUBE_RESPONSE_KEY
import cat.oscarromero.data.dto.videoinfo.VideoInfoDto
import cat.oscarromero.domain.model.VideoUrl
import cat.oscarromero.domain.repository.VideoInfoRepository
import cat.oscarromero.domain.usecase.Error
import cat.oscarromero.domain.usecase.Result
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URLDecoder
import javax.inject.Inject

class VideoInfoRepositoryNetwork @Inject constructor() : VideoInfoRepository {
    override suspend fun getVideoUrl(videoId: String): Result<Error, VideoUrl> {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(String.format(YOUTUBE_INFO_URL, videoId))
            .build()

        return client.newCall(request).execute().use { response ->
            response.body()?.let {
                try {
                    val resp = URLDecoder.decode(it.string(), "UTF-8")

                    val data: String = resp
                        .split("&")
                        .find { it.startsWith(YOUTUBE_RESPONSE_KEY) } ?: ""

                    val json = data.replace(YOUTUBE_RESPONSE_KEY, "")

                    val dto = Gson().fromJson(json, VideoInfoDto::class.java)
                    if (dto.playabilityStatus.status.toUpperCase() == "OK") {
                        Result.Success(
                            VideoUrl(
                                dto.streamingData?.dashManifestUrl
                                    ?: dto.streamingData?.adaptiveFormats?.get(0)?.url.toString()
                            )
                        )
                    } else {
                        Result.Failure(Error.UnknownError(dto.playabilityStatus.messages[0]))
                    }
                } catch (exception: Exception) {
                    Result.Failure(Error.UnknownError())
                }
            } ?: Result.Failure(Error.ServerError("Response is null"))
        }
    }
}
