package cat.oscarromero.domain.usecase

sealed class Error {
    data class NetworkConnectionError(val message: String) : Error()
    data class ServerError(val message: String) : Error()
    object UnknownError : Error()
}
