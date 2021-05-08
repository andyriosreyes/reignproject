package com.prueba.andyrios.domain.usecase

import com.prueba.andyrios.domain.usecase.base.SingleUseCase
import com.prueba.andyrios.domain.model.ArticleMain
import com.prueba.andyrios.domain.repository.ArticleRepository
import com.prueba.andyrios.presentation.response.ArticleResponse
import io.reactivex.Single
import javax.inject.Inject

class GetArticleMainUseCase @Inject constructor(private val articleMainRepository: ArticleRepository) : SingleUseCase<ArticleMain>() {
    override fun buildUseCaseSingle(): Single<ArticleMain> {
        return articleMainRepository.getArticlesMain()
    }

    fun addArticlesDAO(articles: List<ArticleResponse>) {
        articleMainRepository.addArticlesDAO(articles)
    }

    fun allArticlesDAO(): List<ArticleResponse> {
        return articleMainRepository.getAllArticlesDAO()
    }

    fun allArticlesNoFilterDAO(): List<ArticleResponse> {
        return articleMainRepository.getAllArticlesNoFilterDAO()
    }

    fun deleteArticleDAO(id : String) {
        articleMainRepository.deleteArticleDAO(id)
    }
}