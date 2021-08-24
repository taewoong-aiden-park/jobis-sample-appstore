package co.jobis.sample.appstore.data.model.remote.response

import co.jobis.sample.appstore.domain.entity.AppInfo

data class GetAppsResponse(
    val resultCount: Double?,
    val results: List<AppInfo> = listOf(),
)