package co.jobis.sample.appstore.domain.usecase

import co.jobis.sample.appstore.domain.Result
import co.jobis.sample.appstore.domain.repository.AppStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddHistory(
    private val appStoreRepository: AppStoreRepository
) : UseCase<AddHistory.Input, AddHistory.Output>() {

    override suspend fun execute(parameter: Input): Flow<Result<Output>> = flow {
        appStoreRepository.addHistory(parameter.terms)
//        emit(Result.success(Output()))
    }

    class Input(
        val terms: String
    ) : UseCase.Input

    class Output(

    ) : UseCase.Output
}