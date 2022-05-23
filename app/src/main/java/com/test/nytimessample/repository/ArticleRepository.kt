package com.test.nytimessample.repository

import com.test.nytimessample.ApiService
import com.test.nytimessample.model.ArticleResponse
import retrofit2.Response

interface ArticleRepository {
   suspend fun getArticleList(period:Int):Response<ArticleResponse>
}

class ArticleRepositoryImpl(private val apiService: ApiService) : ArticleRepository{

    override suspend fun getArticleList(period: Int): Response<ArticleResponse> {
        return apiService.getArticleList(period)
    }

}