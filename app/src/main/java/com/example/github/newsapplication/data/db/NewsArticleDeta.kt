//package com.example.github.newsapplication.data.db
//
//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.Index
//import androidx.room.PrimaryKey
//import com.example.github.newsapplication.entity.NewsSource
//
///**
// *   Created by zhangziyi on 9/14/20 18:22
// */
//@Entity(
//    tableName = "news_article_cache"
//)
//data class MyNewsData(
//    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
//    @ColumnInfo(name = "cache_id")val cache_id: Long = 0,
//
//    @ColumnInfo(name = "author")val author :String,
//    @ColumnInfo(name = "title")val title : String,
//    @ColumnInfo(name = "description")val description : String,
//    @ColumnInfo(name = "url")val url: String,
//    @ColumnInfo(name = "urlToImage")val urlToImage : String,
//    @ColumnInfo(name = "publishedAt")val publishedAt :String,
//    @ColumnInfo(name = "content")val  content : String
//)
//
