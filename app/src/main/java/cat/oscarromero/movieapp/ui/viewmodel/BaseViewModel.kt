package cat.oscarromero.movieapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cat.oscarromero.domain.usecase.Error

abstract class BaseViewModel : ViewModel() {

    val failureLiveData = MutableLiveData<Error>()
    val loadingLiveData = MutableLiveData<Boolean>()

    protected fun handleFailure(error: Error) {
        failureLiveData.value = error
    }

    protected fun showLoading() {
        loadingLiveData.value = true
    }

    protected fun hideLoading() {
        loadingLiveData.value = false
    }
}
