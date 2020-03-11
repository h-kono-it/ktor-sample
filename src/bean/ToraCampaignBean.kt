package com.example.ktor.bean

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * とらのあなキャンペーンの1データを扱うbeanクラス
 */
data class ToraCampaignBean(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("description")
    val description: String,
    @JsonProperty("campaign_url")
    val campaignUrl: String,
    @JsonProperty("image_url")
    val imageUrl: String,
    @JsonProperty("publish_at")
    val publishAt: String,
    @JsonProperty("publish_at_unix_timestamp")
    val publishAtUnixTimestamp: Int,
    @JsonProperty("start_at")
    val startAt: String,
    @JsonProperty("start_at_unix_timestamp")
    val startAtUnixTimestamp: Int,
    @JsonProperty("end_at")
    val endAt: String,
    @JsonProperty("end_at_unix_timestamp")
    val endAtUnixTimestamp: Int,
    @JsonProperty("is_adult")
    val isAdult: Boolean
)