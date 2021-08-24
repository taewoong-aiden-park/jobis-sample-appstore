package co.jobis.sample.appstore.domain.usecase

import co.jobis.sample.appstore.domain.Result
import co.jobis.sample.appstore.domain.entity.AppInfo
import co.jobis.sample.appstore.domain.repository.AppStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GetApps(
    private val appStoreRepository: AppStoreRepository,
) : UseCase<GetApps.Input, GetApps.Output>() {

    override suspend fun execute(parameter: Input): Flow<Result<Output>> {
        return appStoreRepository.getApps(parameter.terms)
            .map { it.filter { it.screenshotUrls.isNotEmpty() } }
            .map { Result.success(Output(it)) }
            .catch { Result.failure<Output>(it) }
    }

    class Input(
        val terms: String
    ) : UseCase.Input

    class Output(
        val apps: List<AppInfo>
    ) : UseCase.Output
}