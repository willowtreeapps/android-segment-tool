package com.example.analyticstestinglibrary.dataobjects.segment

import com.squareup.moshi.Json

data class Properties(
    val version: String? = null,
    @Json(name = "primary_business_unit")
    val primaryBusinessUnit: String? = null,
    @Json(name = "secondary_business_unit")
    val secondaryBusinessUnit: String? = null,
    @Json(name = "app_name")
    val appName: String? = null,
    @Json(name = "app_platform")
    val appPlatform: String? = null,
    @Json(name = "app_version")
    val appVersion: String? = null,
    @Json(name = "page_name")
    val pageName: String? = null,
    @Json(name = "page_type")
    val pageType: String? = null,
    val livestream: String? = null,
    @Json(name = "video_player")
    val videoPlayer: String? = null,
    @Json(name = "video_player_state")
    val videoPlayerState: String? = null,
    @Json(name = "video_asset_title")
    val videoAssetTitle: String? = null,
    @Json(name = "video_asset_id")
    val videoAssetID: String? = null,
    @Json(name = "video_primary_business_unit")
    val videoPrimaryBusinessUnit: String? = null,
    @Json(name = "video_secondary_business_unit")
    val videoSecondaryBusinessUnit: String? = null,
    @Json(name = "video_content_length")
    val videoContentLength: String? = null,
    @Json(name = "video_content_type")
    val videoContentType: String? = null,
    @Json(name = "video_seconds_viewed")
    val videoSecondsViewed: String? = null,
    @Json(name = "video_airplay")
    val videoAirplay: String? = null,
    @Json(name = "video_first_air_date")
    val videoFirstAirDate: String? = null,
    @Json(name = "total_length")
    val totalLength: String? = null,
    @Json(name = "asset_id")
    val assetID: String? = null
)