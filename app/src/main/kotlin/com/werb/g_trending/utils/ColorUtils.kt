package com.werb.g_trending.utils

import android.content.Context
import com.google.gson.Gson
import com.werb.g_trending.model.Language

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/18. */

object ColorUtils {

    lateinit var colors: Colors

    fun load(context: Context) {
        val json = ResourcesUtils.getFromAssets(context, "colors.json")
        colors = Gson().fromJson(json, Colors::class.java)
    }

    fun getColor(languageName: String): Language? {
        val language = colors.colors[languageName]
        language?.name = languageName
        return language
    }

    data class Colors(var colors: Map<String, Language> = hashMapOf())
}
