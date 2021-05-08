package com.prueba.andyrios.di

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import com.prueba.andyrios.data.source.local.DataBase
import com.prueba.andyrios.data.source.local.dao.ArticleDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): DataBase {
        return Room.databaseBuilder(
            application,
            DataBase::class.java,
            DataBase.DB_NAME
        ).allowMainThreadQueries().build()
    }


    @Provides
    internal fun provideArticleDao(appDatabase: DataBase): ArticleDAO {
        return appDatabase.articleDao
    }
}