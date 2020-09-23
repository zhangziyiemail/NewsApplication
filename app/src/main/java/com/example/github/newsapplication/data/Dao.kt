package com.example.github.newsapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.github.newsapplication.entity.NewsData
import com.example.github.newsapplication.entity.User


/**
 *   Created by zhangziyi on 9/14/20 18:20
 */
@Dao
interface NewsArticleCacheDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheHomeArticles(articles: NewsData):Long

    @Query("select * from news_article_cache WHERE title = :title")
    suspend fun findArticle(title : String): MutableList<NewsData>

    @Query("select * from news_article_cache ")
    suspend fun loadAllArticles(): MutableList<NewsData>

    @Query("delete from news_article_cache where title = :title")
    suspend fun clearHomeCache(title: String): Int



}