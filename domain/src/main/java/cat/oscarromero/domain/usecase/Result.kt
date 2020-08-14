package cat.oscarromero.domain.usecase

sealed class Result<out F : Error, out R> {
    data class Success<out R>(val successData: R) : Result<Nothing, R>()
    data class Failure<out F : Error>(val errorData: F) : Result<F, Nothing>()
}
