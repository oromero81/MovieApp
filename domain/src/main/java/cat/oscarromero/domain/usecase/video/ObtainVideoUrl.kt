package cat.oscarromero.domain.usecase.video

import cat.oscarromero.domain.model.VideoUrl
import cat.oscarromero.domain.repository.VideoInfoRepository
import cat.oscarromero.domain.usecase.Error
import cat.oscarromero.domain.usecase.Result
import cat.oscarromero.domain.usecase.UseCase
import javax.inject.Inject

class ObtainVideoUrl @Inject constructor(private val videoInfoRepository: VideoInfoRepository) :
    UseCase<VideoUrl, String>() {

    override suspend fun run(params: String): Result<Error, VideoUrl> =
        videoInfoRepository.getVideoUrl(params)
}
