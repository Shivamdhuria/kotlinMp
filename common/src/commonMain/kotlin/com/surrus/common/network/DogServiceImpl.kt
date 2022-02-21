package com.surrus.common.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import com.surrus.common.network.models.DogDto

class DogServiceImpl: DogService {

    private val BASE_URL = "https://dog.ceo/api"
    val json = Json { isLenient = true; ignoreUnknownKeys = true }

    private val client: HttpClient = HttpClient() {
        install(ContentNegotiation) {
            json(json)
        }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
    }

//    fun createHttpClient(httpClientEngine: HttpClientEngine, json: Json, enableNetworkLogs: Boolean) = HttpClient(httpClientEngine) {
//        install(ContentNegotiation) {
//            json(json)
//        }
//        if (enableNetworkLogs) {
//            install(Logging) {
//                logger = Logger.DEFAULT
//                level = LogLevel.INFO
//            }
//        }
//    }


    override suspend fun getDogs(): DogDto {
        return client.get("$BASE_URL/breeds/image/random/4").body<DogDto>()
    }
}



