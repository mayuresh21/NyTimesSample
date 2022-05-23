package com.test.nytimessample.ui

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.nytimessample.adapter.ArticleListAdapter
import com.test.nytimessample.model.ArticleResponse
import com.test.nytimessample.model.Media
import com.test.nytimessample.model.Result
import com.test.nytimessample.util.Resource

object ArticleView {


    @BindingAdapter("app:items")
    @JvmStatic fun setItems(recyclerView: RecyclerView, resource: Resource<ArticleResponse>?) {
        /*
        with(recyclerView.adapter as ArticleListAdapter) {
            resource?.data?.let {
                Log.d("Data=> ",""+it.results.size)
//                updateData(it)
            }
        }*/
    }

    @BindingAdapter("app:imageUrl")
    @JvmStatic fun loadImage(view: ImageView, list: List<Media>) {
        if(!list.isNullOrEmpty() && !list.get(0).mediaMetadata.isNullOrEmpty()){
            val index = list.get(0).mediaMetadata.size
            Glide.with(view.context)
                .load(Uri.parse(list.get(0).mediaMetadata.get(index-1).url))
                .into(view)
        }
    }
}