package co.jobis.sample.appstore.di

import android.content.Context
import co.jobis.sample.appstore.data.database.AppStoreDatabase
import co.jobis.sample.appstore.data.database.dao.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppStoreDatabase(
        @ApplicationContext context: Context
    ): AppStoreDatabase {
        return AppStoreDatabase.buildDatabase(context)
    }

    @Singleton
    @Provides
    fun provideHistoryDao(
        appStoreDatabase: AppStoreDatabase
    ): HistoryDao {
        return appStoreDatabase.historyDao()
    }
}