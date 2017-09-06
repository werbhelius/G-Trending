package com.werb.g_trending.api

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/6. */

object TrendingRequest {

    private val baseUrl = "https://github.com/trending"

    fun loadData(lang: String?){

        lang?.let {
            val url = "$baseUrl/$lang"
            request(url)
        } ?: request(baseUrl)

    }

    private fun request(url: String){
        val document: Document = Jsoup.connect(url).get()
        val repoList = document.select(".repo-list")
        if (repoList.isNotEmpty()) {
            val list: Elements? = repoList.select("li")
            list?.let {
                if (list.isNotEmpty()) {
                    it.onEach {
                        val title = it.select(".d-inline-block > h3 > a").text()
                        val description = it.select(".py-1 > p").text()
                        val stars = it.select(".f6 > a[href*=/stargazers]").text()
                        val forks = it.select(".f6 > a[href*=/network]").text()
                        var todayStars = it.select(".f6 > span.float-right").text()
                        if (todayStars.isNullOrBlank()) {
                            todayStars = it.select(".f6 > span.float-sm-right").text()
                        }
                        var language = it.select(".f6 .mr-3 > span[itemprop=programmingLanguage]").text()
                        if (language.isNullOrBlank()) {
                            language = it.select(".f6 span[itemprop=programmingLanguage]").text()
                        }
                    }
                }
            }
        }
    }

}