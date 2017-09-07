package com.werb.g_trending.utils

import android.content.Context

/**
 * Created by liuxi on 2017/9/7.
 */
class ResourcesUtils {

    companion object {
        fun dp2px(context: Context, dipValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f).toInt()
        }
    }

}