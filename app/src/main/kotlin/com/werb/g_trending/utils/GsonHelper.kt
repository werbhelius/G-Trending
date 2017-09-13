package com.werb.g_trending.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/13. */

object GsonHelper {

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