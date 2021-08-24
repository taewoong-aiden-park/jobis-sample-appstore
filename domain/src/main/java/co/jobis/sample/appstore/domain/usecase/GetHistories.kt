package co.jobis.sample.appstore.domain.usecase

import co.jobis.sample.appstore.domain.Result
import co.jobis.sample.appstore.domain.entity.History
import co.jobis.sample.appstore.domain.repository.AppStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GetHistories(
    private val appStoreRepository: AppStoreRepository
) : UseCase<GetHistories.Input, GetHistories.Output>() {

    override suspend fun execute(parameter: Input): Flow<Result<Output>> {
        return appStoreRepository.getHistories()
            .map { Result.success(Output(it)) }
            .catch { Result.failure<Output>(it) }
    }

    class Input(

    ) : UseCase.Input

    class Output(
        val histories: List<History>
    ) : UseCase.Output
}