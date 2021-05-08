package com.prueba.andyrios.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prueba.andyrios.data.source.local.dao.ArticleDAO
import com.prueba.andyrios.presentation.response.ArticleResponse

@Database(entities = [ArticleResponse::class], version = 1, exportSchema = false)
abstract class DataBase()  : RoomDatabase() {
    abstract val articleDao: ArticleDAO

    companion object {
        const val DB_NAME = "REIGN.db"
    }
}
