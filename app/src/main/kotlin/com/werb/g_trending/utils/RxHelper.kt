package com.werb.g_trending.utils

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

object RxHelper {

    fun <T> getObservable(observable: Observable<T>): Observable<T> {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> getPostObservable(observable: Observable<T>): Observable<T> {
        return observable
                .subscribeOn(Schedulers.io())
    }
}