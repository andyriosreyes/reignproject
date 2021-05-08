package com.prueba.andyrios.data.source.local.dao

import androidx.room.*
import com.prueba.andyrios.presentation.response.ArticleResponse

@Dao
interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: ArticleResponse): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<ArticleResponse>)

    @Query("SELECT * FROM ArticleTable")
    fun loadListArticlesNoFilterAll(): MutableList<ArticleResponse>

    @Query("SELECT * FROM ArticleTable WHERE state=1")
    fun loadListArticlesAll(): MutableList<ArticleResponse>

    @Query("UPDATE ArticleTable SET state=0 WHERE objectID = :id ")
    fun deleteArticle(id: String)

    @Query("DELETE FROM ArticleTable")
    fun deleteAll()

    @Query("SELECT * FROM ArticleTable where story_id = :storyID")
    fun loadOneByArticle(storyID: Long): ArticleResponse?

    @Update
    fun update(article: ArticleResponse)
}