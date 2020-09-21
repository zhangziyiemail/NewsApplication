package com.kuky.demo.wan.android.network


import com.example.github.newsapplication.entity.NewWorkState
import retrofit2.http.*

/**
 * @author lee zhang.
 * @description
 */
interface ApiService {
    @GET("top-headlines?country=us")
    suspend fun homeArticles(): NewWorkState
}