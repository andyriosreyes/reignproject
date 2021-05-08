package com.prueba.andyrios.data.repository

import com.prueba.andyrios.data.source.local.DataBase
import com.prueba.andyrios.data.source.remote.RetrofitService
import com.prueba.andyrios.domain.model.ArticleMain
import com.prueba.andyrios.domain.repository.ArticleRepository
import com.prueba.andyrios.presentation.response.ArticleResponse
import io.reactivex.Single

class ArticleRepositoryImp(
    private val database: DataBase,
    private val retrofitService: RetrofitService) : ArticleRepository {

    override fun getArticlesMain(): Single<ArticleMain> {
        return retrofitService.getArticlesMain()
    }

    override fun addArticlesDAO(articles: List<ArticleResponse>) {
        database.articleDao.insertAll(articles)
    }

    override fun getAllArticlesDAO(): List<ArticleResponse> {
        return database.articleDao.loadListArticlesAll()
    }

    override fun getAllArticlesNoFilterDAO(): List<ArticleResponse> {
        return database.articleDao.loadListArticlesNoFilterAll()
    }

    override fun deleteArticleDAO(id: String) {
        database.articleDao.deleteArticle(id)
    }


}