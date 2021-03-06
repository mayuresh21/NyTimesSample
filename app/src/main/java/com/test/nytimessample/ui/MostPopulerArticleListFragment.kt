package com.test.nytimessample.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.nytimessample.R
import com.test.nytimessample.adapter.ArticleListAdapter
import com.test.nytimessample.databinding.FragmentMostPopulerArticleListBinding
import com.test.nytimessample.model.Result
import com.test.nytimessample.util.Status
import com.test.nytimessample.viewmodel.ArticleViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MostPopulerArticleListFragment() : Fragment(),ArticleListAdapter.OnArticleClickListener {

    private val viewModel by sharedViewModel<ArticleViewModel>()

    private lateinit var binding: FragmentMostPopulerArticleListBinding
    private lateinit var adapter: ArticleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMostPopulerArticleListBinding.inflate(inflater,container,false)
        init()
        viewModel.fetchArticleList()
        return binding.root
    }

    fun init(){
        adapter = ArticleListAdapter(viewModel,this)
        binding.recArticle.adapter = adapter
        val layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        binding.recArticle.layoutManager = layoutManager
    }

    override fun onResume() {
        super.onResume()
        setObserver()
    }

    private fun setObserver() {
        viewModel.aList.observe(viewLifecycleOwner,{
//            adapter.updateData(it.data?.results?: listOf())
            when (it.status) {
                Status.SUCCESS -> {
                    binding.txtNoData.visibility = View.GONE
                    adapter.updateData(it.data?.results?: listOf())
                    binding.recArticle.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                    //Handle Error
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onArticleClick(item: Result) {
        Log.d("FRAGMENT=>"," "+item)
        viewModel.setCurrentArticle(item)
        Navigation.findNavController(binding.recArticle).navigate(R.id.action_mostPopulerArticleListFragment_to_articleDetailsFragment)
    }
}