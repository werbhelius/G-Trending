package com.werb.g_trending.api

import com.google.gson.Gson
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.Type

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/13. */

class TrendingFactory : Converter.Factory() {

    private val gson = Gson()

    override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
        return if (type === String::class.java) {
            StringResponseConverter()
        } else GsonConverterFactory.create(gson).responseBodyConverter(type, annotations, retrofit)
    }

    override fun requestBodyConverter(type: Type?, parameterAnnotations: Array<out Annotation>?, methodAnnotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody>? {
        return GsonConverterFactory.create(gson).requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
    }

    private class StringResponseConverter : Converter<ResponseBody, String> {
        @Throws(IOException::class)
        override fun convert(value: ResponseBody): String {
            return value.string()
        }
    }
}