package co.jobis.sample.appstore.data.source

import co.jobis.sample.appstore.domain.entity.AppInfo
import co.jobis.sample.appstore.domain.entity.History
import kotlinx.coroutines.flow.Flow

interface AppStoreDataSource {

    fun getApps(terms: String): Flow<List<AppInfo>>

    fun getHistories(): Flow<List<History>>

    suspend fun addHistory(terms: String)
}