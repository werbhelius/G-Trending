package com.werb.g_trending.utils

import android.content.Context
import com.google.gson.Gson
import com.werb.g_trending.model.Language
import io.reactivex.Observable

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/18. */

object ColorUtils {

    lateinit var colors: Colors

    fun load(context: Context) {
        RxHelper.getObservable(Observable.create<String> {
            val json = ResourcesUtils.getFromAssets(context, "colors.json")
            it.onNext(json)
        }).subscribe {
            colors = Gson().fromJson(it, Colors::class.java)
        }
    }

    fun getLanguage(languageName: String): Language? {
        val language = colors.colors[languageName]
        language?.name = languageName
        return language
    }

    data class Colors(var colors: Map<String, Language> = hashMapOf())
}
