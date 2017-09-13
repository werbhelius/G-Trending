package com.werb.g_trending.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


/** Created by wanbo <werbhelius@gmail.com> on 2017/9/13. */

object JSoupProvider {
    private var okHttpClient: OkHttpClient? = null

    private fun provideOkHttpClient(): OkHttpClient? {
        if (okHttpClient == null) {
            val client = OkHttpClient.Builder()
            okHttpClient = client.build()
        }
        return okHttpClient
    }

    fun getTrendingService(): TrendingService {

        return Retrofit.Builder()
                .baseUrl("https://github.com/trending/")
                .client(provideOkHttpClient())
                .addConverterFactory(TrendingFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(TrendingService::class.java)
    }

}