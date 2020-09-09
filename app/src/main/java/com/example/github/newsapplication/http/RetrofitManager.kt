package com.kuky.demo.wan.android.network


import com.example.github.newsapplication.Utils.LogUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * @author lee zhang.
 * @description
 */
object RetrofitManager {
    private var BASE_URL = "https://newsapi.org/v2/"

    val apiService: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(genericOkClient())
            .build().create(ApiService::class.java)

    }

    private fun genericOkClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(
            object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {

                    // 如果是 json 格式内容则打印 json
                    if ((message.startsWith("{") && message.endsWith("}")) ||
                        (message.startsWith("[") && message.endsWith("]"))
                    )
                        LogUtils.json(message)
                    else
                        LogUtils.verbose(message)
                }
            })
        var interceptor=Interceptor{chain ->
            chain.proceed( chain.request()
                .newBuilder()
                .addHeader("X-Api-Key","add8b7e1169f4d5eb5d3a5cc4fdea2ac")
                .method(chain.request().method, chain.request().body)
                .build())


        }

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(5_000L, TimeUnit.MILLISECONDS)
            .readTimeout(10_000, TimeUnit.MILLISECONDS)
            .writeTimeout(30_000, TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .build()

    }
}