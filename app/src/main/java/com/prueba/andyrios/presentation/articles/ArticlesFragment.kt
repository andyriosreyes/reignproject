package com.prueba.andyrios.presentation.articles

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prueba.andyrios.R
import com.prueba.andyrios.databinding.FragmentArticlesBinding
import com.prueba.andyrios.presentation.response.ArticleResponse
import dagger.hilt.android.AndroidEntryPoint
import pl.kitek.rvswipetodelete.SwipeToDeleteCallback

@AndroidEntryPoint
class ArticlesFragment : Fragment(),OnArticlesAdapterListener {
    private lateinit var fragmentArticlesBinding: FragmentArticlesBinding
    private var adapter: ArticlesAdapter? = null
    private var mCallback: OnArticleCallBack? = null

    private val viewModel: ArticlesViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnArticleCallBack) {
            mCallback = context
        } else throw ClassCastException(context.toString() + "must implement OnArticleCallBack!")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ArticlesAdapter(this)
        viewModel.loadArticles(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentArticlesBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_articles,
            container,
            false
        )
        fragmentArticlesBinding.listArticlesViewModel = viewModel
        fragmentArticlesBinding.articlesRV.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        fragmentArticlesBinding.articlesRV.adapter = adapter

        viewModel.articlesReceivedLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                initRecyclerView(it)
            }
        })

        fragmentArticlesBinding.itemsswipetorefresh.setOnRefreshListener {
            viewModel.loadArticles(requireContext())
            fragmentArticlesBinding.itemsswipetorefresh.isRefreshing = false
        }

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = fragmentArticlesBinding.articlesRV.adapter as ArticlesAdapter
                val position = viewHolder.adapterPosition
                val article = adapter.getItemArticle(position)
                viewModel.deleteArticle(article.objectID)
                adapter.removeAt(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(fragmentArticlesBinding.articlesRV)

        return fragmentArticlesBinding.root
    }

    private fun initRecyclerView(articles: List<ArticleResponse>) {
        adapter?.addData(articles.sortedBy { it.create_id })
    }

    override fun onDetach() {
        super.onDetach()
        adapter = null
        mCallback = null
    }

    companion object {

        val FRAGMENT_NAME = ArticlesFragment::class.java.name

        @JvmStatic
        fun newInstance() =
            ArticlesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun showWeb(article: ArticleResponse) {
        mCallback?.navigateToArticleInfo(article)
    }
}