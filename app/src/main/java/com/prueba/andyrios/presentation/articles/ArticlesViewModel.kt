package com.prueba.andyrios.presentation.articles

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prueba.andyrios.domain.usecase.GetArticleMainUseCase
import com.prueba.andyrios.presentation.mapper.ArticleMapper.toDomain
import com.prueba.andyrios.presentation.response.ArticleResponse
import com.prueba.andyrios.util.isConnected

class ArticlesViewModel @ViewModelInject constructor(private val getArticlesMainUseCase: GetArticleMainUseCase) : ViewModel() {
    val articlesReceivedLiveData = MutableLiveData<List<ArticleResponse>>()
    val isLoad = MutableLiveData<Boolean>()
    val articleData = MutableLiveData<ArticleResponse>()

    init {
        isLoad.value = false
    }

    val article: ArticleResponse? get() = articleData.value

    fun set(article: ArticleResponse) = run { articleData.value = article }

    fun deleteArticle(id: String){
        getArticlesMainUseCase.deleteArticleDAO(id)
    }

    fun loadArticles(context: Context) {
        getArticlesMainUseCase.execute(
            onSuccess = {
                isLoad.value = true
                if(articlesReceivedLiveData.value?.size?:0>0){
                    val articlesDAO = getArticlesMainUseCase.allArticlesNoFilterDAO()
                    val articlesActually = it.toDomain().hits
                    val resultado = articlesActually.filter { it.objectID !in articlesDAO.map { item -> item.objectID }}
                    getArticlesMainUseCase.addArticlesDAO(resultado)
                }else{
                    getArticlesMainUseCase.addArticlesDAO(it.toDomain().hits)
                }
                articlesReceivedLiveData.value = getArticlesMainUseCase.allArticlesDAO()

            },
            onError = {
                if (!isConnected(context)) {
                    val articles = getArticlesMainUseCase.allArticlesDAO()
                    articlesReceivedLiveData.value = articles
                }
                it.printStackTrace()
            }
        )
    }
}