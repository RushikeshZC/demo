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

inline fun <T, E: Error> Result<T, E>.onSuccess(action: (T) -> Unit):  Result<T, E> {
   return when(this) {
        is Result.Error -> {
            return this
        }
        is Result.Success -> {
            action(data)
            this
        }
    }
}

inline fun <T, E: Error> Result<T, E>.onError(action: (E) -> Unit): Result<T, E> {
    return when(this) {
        is Result.Error -> {
            action(error)
            this
        }
        is Result.Success -> {
            this
        }
    }
}

enum class DomainError: Error {
    UNKNOWN_HOST,
    SERIALIZATION,
    UNKNOWN,
    SERVER_ERROR;
}



