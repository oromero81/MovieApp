package cat.oscarromero.domain.usecase

sealed class Error(val errorMessage: String) {
    data class NetworkConnectionError(val message: String) : Error(message)
    data class ServerError(val message: String) : Error(message)
    data class UnknownError(val message: String = "") : Error(message)
}
