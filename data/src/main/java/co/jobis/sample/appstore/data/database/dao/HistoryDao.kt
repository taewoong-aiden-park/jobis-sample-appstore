package co.jobis.sample.appstore.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.jobis.sample.appstore.data.model.local.LocalHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history ORDER BY timestamp DESC") // TODO sorting 로직 추가할 것
    fun getHistories(): Flow<List<LocalHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHistory(history: LocalHistory)
}