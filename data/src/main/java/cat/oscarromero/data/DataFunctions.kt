package cat.oscarromero.data

import cat.oscarromero.data.adapter.NetworkResponse
import cat.oscarromero.domain.usecase.Error
import cat.oscarromero.domain.usecase.Result

fun handleGenericResponseError(response: GenericResponse<Any>): Result<Error, Nothing> {
    return when (response) {
        is NetworkResponse.ApiError ->
            Result.Failure(Error.ServerError("${response.code} - ${response.body.message}"))
        is NetworkResponse.NetworkError ->
            Result.Failure(
                Error.NetworkConnectionError(response.exception.message ?: "Error without message")
            )
        is NetworkResponse.UnknownError ->
            Result.Failure(Error.ServerError(response.error.message ?: "Error without message"))
        else -> Result.Failure(Error.UnknownError)
    }
}
