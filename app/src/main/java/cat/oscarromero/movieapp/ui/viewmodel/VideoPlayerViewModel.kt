package cat.oscarromero.movieapp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import cat.oscarromero.domain.model.VideoUrl
import cat.oscarromero.domain.usecase.Result
import cat.oscarromero.domain.usecase.video.ObtainVideoUrl

class VideoPlayerViewModel @ViewModelInject constructor(private val obtainVideoUrl: ObtainVideoUrl) :
    BaseViewModel() {

    val videoUrlLiveData = MutableLiveData<String>()

    fun obtainVideoUrl(videoId: String) {
        showLoading()
        obtainVideoUrl(videoId) {
            hideLoading()
            when (it) {
                is Result.Success -> handleVideoUrl(it.successData)
                is Result.Failure -> handleFailure(it.errorData)
            }
        }
    }

    private fun handleVideoUrl(videoUrl: VideoUrl) {
        videoUrlLiveData.value = videoUrl.url
    }
}
