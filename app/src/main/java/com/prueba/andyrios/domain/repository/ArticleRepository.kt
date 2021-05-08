package com.prueba.andyrios.domain.repository

import com.prueba.andyrios.domain.model.ArticleMain
import com.prueba.andyrios.presentation.response.ArticleResponse
import io.reactivex.Single

interface ArticleRepository {

    fun getArticlesMain(): Single<ArticleMain>

    fun addArticlesDAO(articles: List<ArticleResponse>)

    fun getAllArticlesDAO() : List<ArticleResponse>

    fun getAllArticlesNoFilterDAO() : List<ArticleResponse>

    fun deleteArticleDAO(id : String)
}