package com.example.ktor.client

/**
 * HTTPClientのインターフェイス
 */
interface KtorHttpClient<T> {
    /**
     * List取得用関数
     * @param url Access先URL
     * @param attributes Header情報
     */
    fun accessListGet(url: String, attributes: Map<String, String>): List<T>
    /**
     * 1件取得用関数
     * @param url Access先URL
     * @param attributes Header情報
     */
    fun accessGet(url: String, attributes: Map<String, String>): List<T>

}