package com.test.nytimessample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nytimessample.model.ArticleResponse
import com.test.nytimessample.model.Result
import com.test.nytimessample.repository.ArticleRepository
import com.test.nytimessample.util.NetworkHelper
import com.test.nytimessample.util.Resource
import kotlinx.coroutines.launch

class ArticleViewModel(
    val articleRepository: ArticleRepository,
    val networkHelper: NetworkHelper
) :ViewModel() {

    var period:Int = 7

    private val _aList = MutableLiveData<Resource<ArticleResponse>>()
    val aList: LiveData<Resource<ArticleResponse>> get() = _aList

    lateinit var dataItem:Result

    fun fetchArticleList() {
        viewModelScope.launch {
            _aList.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                articleRepository.getArticleList(period).let {
                    if (it.isSuccessful) {
                        _aList.postValue(Resource.success(it.body()))
                    } else _aList.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _aList.postValue(Resource.error("No internet connection", null))
        }
    }

    fun setCurrentArticle(item: Result) {
        dataItem = item
    }
}