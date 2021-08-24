package co.jobis.sample.appstore.data.source.remote

import co.jobis.sample.appstore.data.api.AppStoreApiService
import co.jobis.sample.appstore.data.source.AppStoreDataSource
import co.jobis.sample.appstore.domain.entity.AppInfo
import co.jobis.sample.appstore.domain.entity.History
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AppStoreRemoteDataSource(
    private val appStoreApiService: AppStoreApiService
) : AppStoreDataSource {

    override fun getApps(terms: String): Flow<List<AppInfo>> = flow {
        val getAppsResponse = appStoreApiService.getApps(terms)
        emit(getAppsResponse.results)
    }

    override fun getHistories(): Flow<List<History>> {
        throw UnsupportedOperationException()
    }

    override suspend fun addHistory(terms: String) {
        throw UnsupportedOperationException()
    }
}