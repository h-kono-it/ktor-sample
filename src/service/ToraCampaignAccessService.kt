package com.example.ktor.service

import com.example.ktor.client.ToraCampaignHttpClient

/** とらのあなキャンペーンの共通エンドポイント */
private const val endpoint = "https://campaign.toranoana.jp/api/v1/campaign/"

/** 応募可能情報のエンドポイント */
private const val opened = "opened"
/** 期間指定のエンドポイント */
private const val period = "search"
/** 開始日パラメータのkey */
private const val startParamKey = "start_at"
/** 終了日パラメータのkey */
private const val endParamKey = "end_at"
/** 固定のHeader */
private val commonHeaderParam = mapOf("x-tapi-key" to "gijutsu5", "x-tuser-key" to "1008")

/**
 * とらのあなキャンペーンアクセスサービス
 */
class ToraCampaignAccessService {

    /**
     * 応募可能なキャンペーン情報の取得
     * @return キャンペン情報
     */
    fun searchOpenedCampaign() = ToraCampaignHttpClient().accessListGet(endpoint + opened, commonHeaderParam)

    /**
     * 期間を指定した応募可能なキャンペーン情報の取得
     * @param from 開始日
     * @param to 終了日
     * @return キャンペン情報
     */
    fun searchPeriodCampaign(from: String, to: String) =
        ToraCampaignHttpClient().accessListGet(endpoint + period + paramPeriodBuilder(from, to), commonHeaderParam)

    /**
     * 個別のキャンペーン情報の取得
     * @param id キャンペーンID
     * @return キャンペン情報
     */
    fun findCampaign(id: String) = ToraCampaignHttpClient().accessGet(endpoint + id, commonHeaderParam)

    /**
     * 期間指定のURLパラメータ構築
     * @param from 開始日
     * @param to 終了日
     * @return URLパラメータ文字列
     */
    private fun paramPeriodBuilder(from: String, to: String) =
        paramBuilder(mapOf(startParamKey to from, endParamKey to to))

    /**
     * URLパラメータ構築
     * @param params 設定するURLのクエリパラメータのキーと値のマップ
     * @return URLパラメータ文字列
     */
    private fun paramBuilder(params: Map<String, String>) = buildString {
        if (params.isNotEmpty()) {
            append("?")
            params.keys.forEachIndexed { index, key ->
                append(key)
                append("=")
                append(params[key])
                if (index != (params.size - 1)) {
                    append("&")
                }
            }
        }
    }
}