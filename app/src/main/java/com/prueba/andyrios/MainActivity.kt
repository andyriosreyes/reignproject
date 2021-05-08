package com.prueba.andyrios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.prueba.andyrios.presentation.response.ArticleResponse
import com.prueba.andyrios.presentation.articles.ArticlesFragment
import com.prueba.andyrios.presentation.articles.OnArticleCallBack
import com.prueba.andyrios.presentation.info.InfoFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),OnArticleCallBack {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            navigateToMain()
        }
    }

    private fun navigateToMain() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.nav_fragment,
                ArticlesFragment.newInstance(),
                ArticlesFragment.FRAGMENT_NAME
            ).commitAllowingStateLoss()
    }

    override fun navigateToArticleInfo(article: ArticleResponse) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.nav_fragment,
                InfoFragment.newInstance(article.url?:""),
                InfoFragment.FRAGMENT_NAME
            )
            .addToBackStack(InfoFragment.FRAGMENT_NAME)
            .commitAllowingStateLoss()
    }
}