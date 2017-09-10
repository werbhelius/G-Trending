package com.werb.g_trending.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/10. */

object Preference {

    private val THEME = "theme"

    private fun getSharedPreferences(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun setTheme(context: Context, theme: Theme) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(THEME, theme.name)
        editor.apply()
    }

    fun getTheme(context: Context): Theme = Theme.valueOf(getSharedPreferences(context).getString(THEME, Theme.Default.name))

}