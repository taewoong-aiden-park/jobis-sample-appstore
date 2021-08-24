package co.jobis.sample.appstore.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.jobis.sample.appstore.data.database.dao.HistoryDao
import co.jobis.sample.appstore.data.model.local.LocalHistory

const val DATABASE_VERSION = 1

@Database(
    entities = [LocalHistory::class],
    version = DATABASE_VERSION
)
abstract class AppStoreDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "AppStore"

        fun buildDatabase(context: Context): AppStoreDatabase {
            return Room.databaseBuilder(
                context,
                AppStoreDatabase::class.java,
                DATABASE_NAME
            )
                .allowMainThreadQueries()
                .build()
        }
    }

    abstract fun historyDao(): HistoryDao
}