package com.werb.g_trending

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/25. */

object Config {

    var TRENDING_TAG = "repos"

    fun repos(){
        TRENDING_TAG = "repos"
    }

    fun developer() {
        TRENDING_TAG = "developer"
    }

}