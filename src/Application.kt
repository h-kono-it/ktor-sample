package com.example.ktor

import com.example.ktor.service.ToraCampaignAccessService
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.thymeleaf.Thymeleaf
import io.ktor.thymeleaf.ThymeleafContent
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import io.ktor.features.*
import io.ktor.locations.*

fun main(args: Array<String>): Unit = io.ktor.server.tomcat.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Thymeleaf) {
        setTemplateResolver(ClassLoaderTemplateResolver().apply {
            prefix = "templates/thymeleaf/"
            suffix = ".html"
            characterEncoding = "utf-8"
        })
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(Locations) {
    }

    routing {
        /** ルートの処理 */
        get("/") {
            call.respond(
                ThymeleafContent(
                    "index",
                    mapOf("result" to ToraCampaignAccessService().searchOpenedCampaign())
                )
            )
        }

        /** キャンペーン詳細ページの処理 */
        get<CampaignId> {
            call.respond(ThymeleafContent("detail", mapOf("result" to ToraCampaignAccessService().findCampaign(it.id))))
        }

        /** キャンペーン期間指定検索結果ページの処理 */
        get<Period> {
            call.respond(
                ThymeleafContent(
                    "period",
                    mapOf("result" to ToraCampaignAccessService().searchPeriodCampaign(it.from, it.to), "params" to it)
                )
            )
        }
    }
}

/**
 * キャンペーン詳細ページ用パラメータ
 */
@Location("/detail/{id}")
data class CampaignId(val id: String)

/**
 * キャンペーン期間指定検索結果ページ用パラメータ
 */
@Location("/period")
data class Period(val from: String = "", val to: String = "")


