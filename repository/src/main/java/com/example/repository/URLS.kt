package com.example.repository

/**
 * @created on 19/04/25, 2:20 pm
 * @author Rushikesh B
 * @project Zoomcar.
 * CopyRight (c) 2024 Zoomcar. All Rights Reserved.
 */

private const val BASE_URL = "https://api.spaceflightnewsapi.net"

enum class URLS {
    GET_ARTICLES;

    fun getUrl(): String {
        return when (this) {
            GET_ARTICLES -> "/v4/articles"
        }
    }

    fun constructUrl(): String {
       return BASE_URL + getUrl()
    }
}