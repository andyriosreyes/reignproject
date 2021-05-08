package com.prueba.andyrios.presentation.mapper

import com.prueba.andyrios.domain.model.Article
import com.prueba.andyrios.domain.model.ArticleMain
import com.prueba.andyrios.domain.model.ResultData
import com.prueba.andyrios.presentation.response.ArticleMainResponse
import com.prueba.andyrios.presentation.response.ArticleResponse
import com.prueba.andyrios.presentation.response.ResultDataResponse
import com.prueba.andyrios.util.getDatetoLong
import com.prueba.andyrios.util.getTimeAgo

object ArticleMapper {

    fun ArticleMain.toDomain() = ArticleMainResponse(
        hits = hits.map { it.toDomain() }
    )

    private fun Article.toDomain() = ArticleResponse(
            create_at = getTimeAgo(create_at),
            url = url,
            author = author,
            story_id = story_id,
            story_title = story_title?:"No Title",
            create_id = getDatetoLong(create_at),
            state = 1,
            objectID = objectID
            //_highlightResult = _highlightResult.toDomain()
        )

    private fun ResultData.toDomain() = ResultDataResponse(
        story_title = story_title,
    )

}