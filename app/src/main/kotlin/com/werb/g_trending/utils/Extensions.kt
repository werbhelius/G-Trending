package com.werb.g_trending.utils

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/22. */

fun Array<*>.list(): MutableList<String> {
    val list = arrayListOf<String>()
    forEach {
        list.add(it as String)
    }
    return list
}