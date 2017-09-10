package com.werb.g_trending.model

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/6. */

data class Repository(val title: String?,
                      val description: String,
                      val stars: String?,
                      val forks: String?,
                      val color: String,
                      val todayStars: String?,
                      val language: String?,
                      val contributors: String,
                      var users: MutableList<User>) {

    data class User(val name: String, val avatar: String)
}

