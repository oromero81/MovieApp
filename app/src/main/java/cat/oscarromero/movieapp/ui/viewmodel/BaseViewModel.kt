package cat.oscarromero.movieapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cat.oscarromero.domain.usecase.Error

abstract class BaseViewModel : ViewModel() {

    val failureLiveData = MutableLiveData<Error>()

    protected fun handleFailure(error: Error) {
        failureLiveData.value = error
    }
}
