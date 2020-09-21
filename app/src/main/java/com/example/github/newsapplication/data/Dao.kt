//package com.example.github.newsapplication.data
//
//import androidx.paging.DataSource
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.example.github.newsapplication.data.db.MyNewsData
//
//
///**
// *   Created by zhangziyi on 9/14/20 18:20
// */
//@Dao
//interface NewsArticleCacheDao{
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun cacheHomeArticles(articles: List<MyNewsData>): List<Long>
//
//    @Query("select * from news_article_cache")
//    fun fetchAllCache(): DataSource.Factory<Int, MyNewsData>
//
//    @Query("delete from news_article_cache")
//    fun clearHomeCache(): Int
//
//}