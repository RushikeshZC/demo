package com.example.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Articles(

    @SerialName("results")
    val results: List<Result>? = null
)


@Serializable
data class Result(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("image_url")
    val imageUrl: String? = null,

    @SerialName("summary")
    val summary: String? = null
)