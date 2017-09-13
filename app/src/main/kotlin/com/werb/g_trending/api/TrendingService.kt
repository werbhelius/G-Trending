package com.werb.g_trending.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/13. */

interface TrendingService {

    @GET("{lan}") fun getTrending(@Path("lan") lan: String?, @Query("since") since: String?): Observable<String>

}