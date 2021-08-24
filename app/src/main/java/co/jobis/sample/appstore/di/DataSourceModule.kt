package co.jobis.sample.appstore.di

import co.jobis.sample.appstore.data.api.AppStoreApiService
import co.jobis.sample.appstore.data.database.dao.HistoryDao
import co.jobis.sample.appstore.data.source.AppStoreDataSource
import co.jobis.sample.appstore.data.source.local.AppStoreLocalDataSource
import co.jobis.sample.appstore.data.source.remote.AppStoreRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteDataSource

    @Singleton
    @LocalDataSource
    @Provides
    fun provideAppStoreLocalDataSource(
        historyDao: HistoryDao
    ): AppStoreDataSource {
        return AppStoreLocalDataSource(historyDao)
    }

    @Singleton
    @RemoteDataSource
    @Provides
    fun provideAppStoreRemoteDataSource(
        appStoreApiService: AppStoreApiService
    ): AppStoreDataSource {
        return AppStoreRemoteDataSource(appStoreApiService)
    }
}