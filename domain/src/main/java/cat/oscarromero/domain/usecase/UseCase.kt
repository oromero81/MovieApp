package cat.oscarromero.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class UseCase<out Type, in Params> {

    abstract suspend fun run(params: Params): Result<Error, Type>

    operator fun invoke(params: Params, onResult: (Result<Error, Type>) -> Unit) {
        val job = GlobalScope.async(Dispatchers.IO) { run(params) }
        GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }
    }
}
