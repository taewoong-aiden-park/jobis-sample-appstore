package co.jobis.sample.appstore.domain.entity

data class AppInfo(
    val screenshotUrls: List<String> = listOf(),
    val appletvScreenshotUrls: List<String> = listOf(),
    val ipadScreenshotUrls: List<String> = listOf(),
    val artworkUrl60: String?,
    val artworkUrl512: String?,
    val artworkUrl100: String?,
    val artistViewUrl: String?,
    val advisories: List<String> = listOf(),
    val supportedDevices: List<String> = listOf(),
    val isGameCenterEnabled: Boolean,
    val kind: String,
    val features: List<String> = listOf(),
    val minimumOsVersion: String,
    val trackCensoredName: String,
    val languageCodesISO2A: List<String> = listOf(),
    val fileSizeBytes: Int,
    val sellerUrl: String,
    val formattedPrice: String,
    val contentAdvisoryRating: String,
    val averageUserRatingForCurrentVersion: Double,
    val userRatingCountForCurrentVersion: Int,
    val trackViewUrl: String,
    val trackContentRating: String,
    val averageUserRating: Double,
    val genreIds: List<String>,
    val isVppDeviceBasedLicensingEnabled: Boolean,
    val releaseDate: String,
    val sellerName: String,
    val primaryGenreName: String,
    val trackId: Int,
    val trackName: String,
    val currentVersionReleaseDate: String,
    val releaseNotes: String,
    val primaryGenreId: Int,
    val currency: String,
    val version: String,
    val wrapperType: String,
    val artistId: Int,
    val artistName: String,
    val genres: List<String>,
    val price: Double,
    val description: String,
    val bundleId: String,
    val userRatingCount: Int,
//    val screenshotUrls: List<String> = listOf(),
//    val ipadScreenshotUrls: List<String> = listOf(),
//    val smallIcon: String?,
//    val mediumIcon: String?,
//    val largeIcon: String?,
//    val minimumOsVersion: String,
//    val languageCodes: List<String> = listOf(),
//    val fileSizeBytes: Int,
//    val sellerUrl: String,
//    val contentAdvisoryRating: String,
//    val averageUserRatingForCurrentVersion: Double,
//    val userRatingCountForCurrentVersion: Int,
//    val primaryGenreName: String,
//    val appId: Int,
//    val appName: String,
//    val currentVersionReleaseDate: String,
//    val releaseNotes: String,
//    val version: String,
//    val companyId: Int,
//    val companyName: String,
//    val genres: List<String>,
//    val description: String,
//    val bundleId: String,
)
