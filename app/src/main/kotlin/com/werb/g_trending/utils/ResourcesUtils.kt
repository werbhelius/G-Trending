package com.werb.g_trending.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by liuxi on 2017/9/7.
 */
object ResourcesUtils {

    fun dp2px(context: Context, dipValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    fun getFromAssets(context: Context, fileName: String): String {
        var Result = ""
        try {
            val inputReader = InputStreamReader(context.resources.assets.open(fileName))
            val bufReader = BufferedReader(inputReader)
            bufReader.readLines().forEach {
                Result += it
            }
            bufReader.close()
            inputReader.close()
        } catch (e: Exception) {

        }

        return Result
    }

}