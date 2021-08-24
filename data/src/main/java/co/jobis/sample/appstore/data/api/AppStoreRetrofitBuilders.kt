package co.jobis.sample.appstore.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val APPSTORE_BASE_URL = "https://itunes.apple.com"
private const val APPSTORE_BASE_URL_MOCK = "https://97429147-97ef-4eea-9814-6449fe4f3398.mock.pstmn.io"

fun buildAppStoreApiService(): AppStoreApiService {
    return buildRetrofit()
        .create(AppStoreApiService::class.java)
}

private fun buildRetrofit(): Retrofit {
    return Retrofit.Builder()
        .client(buildClient())
        .baseUrl(APPSTORE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun buildClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()
}



