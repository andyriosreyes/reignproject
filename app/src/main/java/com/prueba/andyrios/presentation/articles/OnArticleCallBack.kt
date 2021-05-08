package com.prueba.andyrios.presentation.articles

import com.prueba.andyrios.presentation.response.ArticleResponse

interface OnArticleCallBack {
    fun navigateToArticleInfo(article: ArticleResponse)
}