package com.werb.g_trending.api

import com.werb.g_trending.model.Repository
import com.werb.g_trending.model.User
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/6. */

object TrendingRequest {

    enum class DailyType { TODAY, WEEK, MONTH }

    private val baseUrl = "https://github.com/trending"

    fun repository(lang: String?, daily: DailyType = DailyType.TODAY): Observable<List<Repository>> {
        val url = buildUrl(baseUrl, lang, daily)
        return Observable.create(ObservableOnSubscribe<List<Repository>> {
            it.onNext(requestRepos(url))
            it.onComplete()
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

//    fun developer(lang: String?, daily: DailyType = DailyType.TODAY): Observable<User> {
//        val url = buildUrl("$baseUrl/developer", lang, daily)
//    }

    private fun buildUrl(url: String , lang: String?, daily: DailyType): String {
        return  when (daily) {
            DailyType.TODAY -> "$url/$lang?since=daily"
            DailyType.WEEK -> "$url/$lang?since=weekly"
            DailyType.MONTH -> "$url/$lang?since=monthly"
        }
    }


    private fun requestRepos(url: String): List<Repository> {
        val document: Document = Jsoup.connect(url).get()
        val repoList = document.select(".repo-list")
        val trendingList = mutableListOf<Repository>()
        if (repoList.isNotEmpty()) {
            val list: Elements? = repoList.select("li")
            list?.let {
                if (list.isNotEmpty()) {
                    it.onEach {
                        val title = it.select(".d-inline-block > h3 > a").text()
                        val description = it.select(".py-1 > p").text()
                        val stars = it.select(".f6 > a[href*=/stargazers]").text()
                        val forks = it.select(".f6 > a[href*=/network]").text()
                        val color = it.select(".f6 .mr-3 > span.repo-language-color").attr("style")
                                .replace("background-color:", "").replace(";", "")
                        var todayStars = it.select(".f6 > span.float-right").text()
                        if (todayStars.isNullOrBlank()) {
                            todayStars = it.select(".f6 > span.float-sm-right").text()
                        }
                        var language = it.select(".f6 .mr-3 > span[itemprop=programmingLanguage]").text()
                        if (language.isNullOrBlank()) {
                            language = it.select(".f6 span[itemprop=programmingLanguage]").text()
                        }
                        val aTag = it.select(".f6 span a.no-underline")
                        val contributors = aTag.attr("href")
                        val users = arrayListOf<User>()
                        if (aTag.isNotEmpty()) {
                            val images = aTag.select("img")
                            images.onEach {
                                val name = it.attr("title")
                                val avatar = it.attr("src")
                                User(name, avatar)
                            }
                        }
                        val trending = Repository(title, description, stars, forks, color, todayStars, language, contributors, users)
                        trendingList.add(trending)
                    }
                }
            }
        }
        return trendingList
    }

}