package co.jobis.sample.appstore.di

import co.jobis.sample.appstore.data.repositoryImpl.AppStoreRepositoryImpl
import co.jobis.sample.appstore.data.source.AppStoreDataSource
import co.jobis.sample.appstore.domain.repository.AppStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAppStoreRepository(
        @DataSourceModule.LocalDataSource appStoreLocalDataSource: AppStoreDataSource,
        @DataSourceModule.RemoteDataSource appStoreRemoteDataSource: AppStoreDataSource,
    ): AppStoreRepository {
        return AppStoreRepositoryImpl(appStoreLocalDataSource, appStoreRemoteDataSource)
    }
}