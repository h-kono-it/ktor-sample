package com.example.ktor.client

import com.example.ktor.bean.ToraCampaignBean
import com.example.ktor.bean.ToraCampaignResultBean
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result

/**
 * とらのあなキャンペーン取得用のHTTPClient
 */
class ToraCampaignHttpClient : KtorHttpClient<ToraCampaignBean> {
    /**
     * JSONパーサー
     */
    private val om = jacksonObjectMapper()

    /**
     * List取得用関数
     * @param url Access先URL
     * @param attributes Header情報
     * @return とらのあなキャンペーン情報（取得できた場合：リスト、できなかった場合：空のリスト
     */
    override fun accessListGet(url: String, attributes: Map<String, String>): List<ToraCampaignBean> {
        var resultList = mutableListOf<ToraCampaignBean>()
        val (request, response, result) = Fuel.get(url).header(attributes).responseString()
        when (result) {
            is Result.Failure -> {
            }
            is Result.Success -> {
                val data = result.get()
                val bean : ToraCampaignResultBean<MutableList<ToraCampaignBean>> =  om.readValue(data)
                resultList = bean.data
            }
        }
        return resultList
    }

    /**
     * 1件取得用関数
     * @param url Access先URL
     * @param attributes Header情報
     * @return とらのあなキャンペーン情報（取得できた場合：1件のリスト、できなかった場合：空のリスト
     */
    override fun accessGet(url: String, attributes: Map<String, String>): List<ToraCampaignBean> {
        val resultList = mutableListOf<ToraCampaignBean>()
        val (request, response, result) = Fuel.get(url).header(attributes).responseString()
        when (result) {
            is Result.Failure -> {
            }
            is Result.Success -> {
                val data = result.get()
                val bean : ToraCampaignResultBean<ToraCampaignBean> =  om.readValue(data)
                resultList.add(bean.data)
            }
        }
        return resultList
    }
}