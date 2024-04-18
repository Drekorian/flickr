package cz.drekorian.android.flickr.flickr.impl.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class KtorClient(
    logger: HttpLogger,
) {
    val httpClient = HttpClient {
        install(Logging) {
            level = LogLevel.BODY
            this.logger = logger
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                    useAlternativeNames = true
                }
            )
        }
        defaultRequest {
            host = "api.flickr.com"
            url {
                protocol = URLProtocol.HTTPS
            }
        }
    }
}
