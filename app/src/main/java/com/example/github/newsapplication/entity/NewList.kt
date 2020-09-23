package com.example.github.newsapplication.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 *   Created by zhangziyi on 9/8/20 16:05
 */
data class NewWorkState(
        var status :String,
        var totalResults : Int,
        var articles:MutableList<NewsData>
)
@Parcelize
@Entity(
        tableName = "news_article_cache"
)
data class NewsData(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
        @ColumnInfo(name = "author")var author:String?,
        @ColumnInfo(name = "title")var title: String?,
        @ColumnInfo(name = "description")var description: String?,
        @ColumnInfo(name = "url")var url: String?,
        @ColumnInfo(name = "urlToImage")var urlToImage: String?,
        @ColumnInfo(name = "publishedAt")var publishedAt:String?,
        @ColumnInfo(name = "content")var content: String?
): Parcelable

@Parcelize
data class NewsSource(
        var  id :String,
        var  name: String
): Parcelable