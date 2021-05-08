package com.prueba.andyrios.presentation.articles

import com.prueba.andyrios.presentation.response.ArticleResponse

interface OnArticlesAdapterListener {

    fun showWeb(article : ArticleResponse)
}