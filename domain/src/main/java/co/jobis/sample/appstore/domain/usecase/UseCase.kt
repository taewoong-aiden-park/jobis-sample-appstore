package co.jobis.sample.appstore.domain.usecase

import co.jobis.sample.appstore.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class UseCase<P: UseCase.Input, R: UseCase.Output> internal constructor() {

    suspend operator fun invoke(parameter: P, collectLatest: Boolean = false, callback: suspend (Result<R>) -> Unit) {
        if (collectLatest) {
            val flow = try {
                execute(parameter)
            } catch (e: Exception) {
                flow { emit(Result.failure<R>(e)) }
            }
            flow.catch { emit(Result.failure(it)) }
                .flowOn(Dispatchers.IO)
                .collectLatest { callback(it) }
        } else {
            try {
                execute(parameter)
            } catch (e: Exception) {
                flow { emit(Result.failure<R>(e)) }
            }.catch { emit(Result.failure(it)) }
                .flowOn(Dispatchers.IO)
                .collect { callback(it) }
        }
    }

    suspend operator fun invoke(parameter: P): Flow<Result<R>> {
        return try {
            execute(parameter)
        } catch (e: Exception) {
            flow<Result<R>> { Result.failure<R>(e) }
        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }

    internal abstract suspend fun execute(parameter: P): Flow<Result<R>>

    interface Input

    interface Output
}