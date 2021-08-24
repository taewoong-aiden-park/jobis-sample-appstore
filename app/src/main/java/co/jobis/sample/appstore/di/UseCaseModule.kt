package co.jobis.sample.appstore.di

import co.jobis.sample.appstore.domain.repository.AppStoreRepository
import co.jobis.sample.appstore.domain.usecase.AddHistory
import co.jobis.sample.appstore.domain.usecase.GetApps
import co.jobis.sample.appstore.domain.usecase.GetHistories
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetAppsUseCase(
        appStoreRepository: AppStoreRepository
    ): GetApps {
        return GetApps(appStoreRepository)
    }

    @Singleton
    @Provides
    fun provideGetHistoriesUseCase(
        appStoreRepository: AppStoreRepository
    ): GetHistories {
        return GetHistories(appStoreRepository)
    }

    @Singleton
    @Provides
    fun provideAddHistoryUseCase(
        appStoreRepository: AppStoreRepository
    ): AddHistory {
        return AddHistory(appStoreRepository)
    }
}