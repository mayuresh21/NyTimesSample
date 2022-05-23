package com.test.nytimessample.di

import android.content.Context
import com.test.nytimessample.ApiService
import com.test.nytimessample.repository.ArticleRepository
import com.test.nytimessample.repository.ArticleRepositoryImpl
import com.test.nytimessample.util.AppConstants
import com.test.nytimessample.util.NetworkHelper
import com.test.nytimessample.viewmodel.ArticleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
            Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)

    }
    // single instance of HelloRepository
    single<ArticleRepository> { ArticleRepositoryImpl(get()) }
    single { provideNetworkHelper(androidContext()) }

    viewModel { ArticleViewModel(get(),get()) }

}

fun provideNetworkHelper(context: Context) = NetworkHelper(context)

