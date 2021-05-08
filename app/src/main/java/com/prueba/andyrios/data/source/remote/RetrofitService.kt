package com.prueba.andyrios.data.source.remote

import com.prueba.andyrios.domain.model.ArticleMain
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitService {
    @GET("search_by_date?query=mobile")
    fun getArticlesMain(): Single<ArticleMain>
}