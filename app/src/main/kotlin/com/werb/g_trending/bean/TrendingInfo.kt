package com.werb.g_trending.bean

/**
 * Created by liuxi on 2017/9/6.
 */
data class TrendingInfo(var owner: String,
                        var repository: String,
                        var desc: String,
                        var languageType: String,
                        var iconUrlList: List<String>)