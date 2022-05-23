package com.test.nytimessample

import android.provider.MediaStore
import com.test.nytimessample.model.ArticleResponse
import retrofit2.Response
import retrofit2.http.*
import java.time.Period

interface ApiService {

    @GET("svc/mostpopular/v2/viewed/{period}.json?")
    suspend fun getArticleList(@Path("period") period: Int,@Query("api-key") key:String ="b61Rg7v1AwLS7FM9z6Ri2dhAIJLKvfsX"):Response<ArticleResponse>

}