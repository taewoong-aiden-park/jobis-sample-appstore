package co.jobis.sample.appstore.data.source.local

import co.jobis.sample.appstore.data.database.dao.HistoryDao
import co.jobis.sample.appstore.data.model.local.LocalHistory
import co.jobis.sample.appstore.data.source.AppStoreDataSource
import co.jobis.sample.appstore.domain.entity.AppInfo
import co.jobis.sample.appstore.domain.entity.History
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppStoreLocalDataSource(
    private val historyDao: HistoryDao
) : AppStoreDataSource {
    override fun getApps(terms: String): Flow<List<AppInfo>> {
        throw UnsupportedOperationException()
    }

    override fun getHistories(): Flow<List<History>> {
        return historyDao.getHistories()
            .map { it.map { localHistory -> localHistory.toHistory() } }
    }

    override suspend fun addHistory(terms: String) {
        historyDao.addHistory(LocalHistory(terms, System.currentTimeMillis()))
    }
}