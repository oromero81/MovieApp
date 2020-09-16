package cat.oscarromero.domain.repository

import cat.oscarromero.domain.model.VideoUrl
import cat.oscarromero.domain.usecase.Error
import cat.oscarromero.domain.usecase.Result

interface VideoInfoRepository {

    suspend fun getVideoUrl(videoId: String): Result<Error, VideoUrl>
}
