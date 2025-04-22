package com.example.repository

/**
 * @created on 19/04/25, 12:39 pm
 * @author Rushikesh B
 * @project Zoomcar.
 * CopyRight (c) 2024 Zoomcar. All Rights Reserved.
 */

interface Error

sealed interface Result<out D, out Error> {

    data class Success<out D>(val data: D) : Result<D, Nothing>

    data class Error<Error>(val error: Error) : Result<Nothing, Error>
}

enum class DomainError: Error {
    UNKNOWN_HOST,
    SERIALIZATION,
    UNKNOWN,
    SERVER_ERROR

}



