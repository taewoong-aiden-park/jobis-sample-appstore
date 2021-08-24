package co.jobis.sample.appstore.data.repositoryImpl

import co.jobis.sample.appstore.data.source.AppStoreDataSource
import co.jobis.sample.appstore.domain.entity.AppInfo
import co.jobis.sample.appstore.domain.entity.History
import co.jobis.sample.appstore.domain.repository.AppStoreRepository
import kotlinx.coroutines.flow.Flow

class AppStoreRepositoryImpl(
    private val appStoreLocalDataSource: AppStoreDataSource,
    private val appStoreRemoteDataSource: AppStoreDataSource,
): AppStoreRepository {
    override fun getApps(terms: String): Flow<List<AppInfo>> {
        return appStoreRemoteDataSource.getApps(terms)
    }

    override fun getHistories(): Flow<List<History>> {
        return appStoreLocalDataSource.getHistories()
    }

    override suspend fun addHistory(terms: String) {
        appStoreLocalDataSource.addHistory(terms)
    }
}