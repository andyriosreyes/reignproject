package com.prueba.andyrios.presentation.articles

import androidx.lifecycle.MutableLiveData
import com.prueba.andyrios.presentation.response.ArticleResponse

class ArticleViewModel (val article : ArticleResponse) {

    val articleData = MutableLiveData<ArticleResponse>()

    init {
        articleData.value = article
    }
}