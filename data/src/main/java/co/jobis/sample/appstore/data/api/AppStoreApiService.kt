package co.jobis.sample.appstore.data.api

import co.jobis.sample.appstore.data.model.remote.response.GetAppsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AppStoreApiService {

    @GET("/search")
    suspend fun getApps(
        @Query("term") input: String,
        @Query("entity") entity: String = "iPadSoftware",
        @Query("country") country: String = "kr"
    ): GetAppsResponse
}