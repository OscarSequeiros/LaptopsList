package com.osequeiros.laptoplist.data.remote

import com.osequeiros.laptoplist.data.remote.model.RemoteLaptop
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class LaptopApi @Inject constructor() {

    // Todo: this client could be extracted to an independent file
    private val client = HttpClient(Android) {
        defaultRequest {
            url {
                protocol = URLProtocol.HTTP
                host = HOST_URL
                encodedPath = url.encodedPath
            }
        }
        engine {
            connectTimeout = TIME_OUT_MILLIS

        }
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    suspend fun getPosts(): List<RemoteLaptop> {
        return client.get("list")
    }

    companion object {
        const val HOST_URL = "private-f0eea-mobilegllatam.apiary-mock.com"
        const val TIME_OUT_MILLIS = 10_000
    }
}