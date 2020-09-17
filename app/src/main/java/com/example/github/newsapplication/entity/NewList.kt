package com.example.github.newsapplication.entity

/**
 *   Created by zhangziyi on 9/8/20 16:05
 */

data class NewsData(
        var source : NewsSource,
        var author :String,
        var title : String,
        var description : String,
        var url: String,
        var urlToImage : String,
        var publishedAt :String,
        var  content : String
)

data class NewsSource(
        var  id :String,
        var  name: String
)