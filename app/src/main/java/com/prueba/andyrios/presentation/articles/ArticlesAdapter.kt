package com.prueba.andyrios.presentation.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.prueba.andyrios.R
import com.prueba.andyrios.databinding.ItemArticleBinding
import com.prueba.andyrios.presentation.response.ArticleResponse
import java.util.ArrayList

class ArticlesAdapter (val onArticlesAdapterListener: OnArticlesAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listArticles: MutableList<ArticleResponse> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderArticleBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.item_article, parent, false
        )
        return ArticleViewHolder(holderArticleBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticleViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): ArticleResponse {
        return listArticles[position]
    }

    fun getItemArticle(position: Int): ArticleResponse {
        return listArticles[position]
    }
    override fun getItemCount(): Int {
        return listArticles.size
    }

    fun addData(list: List<ArticleResponse>) {
        this.listArticles.clear()
        this.listArticles.addAll(list)
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        this.listArticles.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ArticleViewHolder(private val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {
        fun onBind(article: ArticleResponse) {
            val holderArticleBinding = dataBinding as ItemArticleBinding
            val articleViewModel = ArticleViewModel(article)
            holderArticleBinding.articleViewModel = articleViewModel

            itemView.setOnClickListener {
                onArticlesAdapterListener.showWeb(article)
            }
        }
    }
}