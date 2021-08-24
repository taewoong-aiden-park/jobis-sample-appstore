package co.jobis.sample.appstore.domain

class Result<T> private constructor(
    private val value: Any?
) {

    val isSuccess = value !is Failure

    val isFailure = value is Failure

    fun getOrNull() = if(isSuccess) value as T else null

    fun getOrDefault(defValue: T) = if(isSuccess) value as T else defValue

    fun throwOnFailure() { if(value is Failure) throw value.t }

    fun getOrThrow(): T {
        throwOnFailure()
        return value as T
    }

    fun exceptionOrNull() = if(value is Failure) value.t else null

    fun <R> map(callback: (T) -> R): Result<R> = if(value is Failure) Result(value) else success(callback(value as T))

    fun <R> mapCatching(callback: (T) -> R): Result<R> = if(value is Failure) Result(value) else try { success(callback(value as T)) } catch (e: Exception) { failure(e) }

    fun <R>fold(onSuccess: (T) -> R, onFailure: (Throwable) -> R): R = if(value is Failure) onFailure(value.t) else onSuccess(value as T)

    fun recover(callback: (Throwable) -> T): Result<T> = if(value !is Failure) this else success(callback(value.t))

    fun onSuccess(callback: (T) -> Unit) = apply { if(isSuccess) callback(value as T) }

    fun onFailure(callback: (Throwable) -> Unit) = apply { if(value is Failure) callback(value.t) }

    companion object {
        fun <V>success(result: V) = Result<V>(result)
        fun <V>failure(throwable: Throwable) = Result<V>(Failure(throwable))
    }

    private class Failure(
        val t: Throwable
    )
}

fun <R>wrappingResult(block: () -> R): Result<R> = try { Result.success(block()) } catch (e: Throwable) { Result.failure<R>(e) }

fun <T, R>T.wrappingResult(block: T.() -> R): Result<R> = try { Result.success(block()) } catch (e: Throwable) { Result.failure<R>(e) }