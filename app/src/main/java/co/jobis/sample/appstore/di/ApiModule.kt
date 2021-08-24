package co.jobis.sample.appstore.di

import co.jobis.sample.appstore.data.api.AppStoreApiService
import co.jobis.sample.appstore.data.api.buildAppStoreApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideAppStoreApiService(): AppStoreApiService {
        return buildAppStoreApiService()
    }

}