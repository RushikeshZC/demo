package com.example.repository

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import java.net.UnknownHostException

/**
 * @created on 19/04/25, 12:44 pm
 * @author Rushikesh B
 * @project Zoomcar.
 * CopyRight (c) 2024 Zoomcar. All Rights Reserved.
 */

suspend inline fun <reified T> safeApiCall(
    execute: () -> HttpResponse
): Result<T, DomainError> {
    val response =
        try {
            execute()
        } catch (e: UnknownHostException) {
            return Result.Error(DomainError.UNKNOWN_HOST)
        }

    return responseToResult<T>(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, DomainError> {
    return when (response.status.value) {
        in 200..299 ->
            try {
                Result.Success<T>(response.body())
            } catch (e: NoTransformationFoundException) {
                Result.Error(DomainError.SERIALIZATION)
            }

        in 500..599 -> Result.Error(DomainError.SERVER_ERROR)
        else -> Result.Error(DomainError.UNKNOWN)
    }
}