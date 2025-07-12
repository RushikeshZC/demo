package com.example.domain

import com.example.repository.DomainError
import com.example.repository.Repository
import com.example.repository.Result
import com.example.repository.URLS

class NewsRepository {
    val repository = Repository()

    suspend fun fetchArticles(): Result<Articles, DomainError> {
        return repository.fetchData(
            url = URLS.GET_ARTICLES
        )
    }
}