package com.example.repository

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * @created on 19/04/25, 1:33 pm
 * @author Rushikesh B
 * @project Zoomcar.
 * CopyRight (c) 2024 Zoomcar. All Rights Reserved.
 */

val client = HttpClient(Android) {
    engine {
        connectTimeout = 60_000
    }
    install(Logging)
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            },
            contentType = ContentType.Application.Json
        )
    }
}

class Repository {

    suspend inline fun <reified T> fetchData(
        url: URLS,
        params: Map<String, String>? = null
    ): Result<T, DomainError> {
        return safeApiCall {
            client.get(urlString = url.constructUrl()) {
                url {
                    params?.forEach {
                        parameters.append(it.key, it.value)
                    }
                }
            }
        }
    }

    suspend inline fun <reified T,  reified P> postData(
        url: URLS,
        data: P
    ): Result<T, DomainError> {
        return safeApiCall {
            client.post(urlString = url.constructUrl()) {
                setBody<P>(data)
            }
        }
    }
}