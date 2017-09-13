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

    fun <T> safeObservable(observable: Observable<T>): Observable<T> {
        return getObservable(observable)
                .doOnError(Consumer<Throwable> { it.printStackTrace() })
    }

    fun <T> getSingle(single: Single<T>): Single<T> {
        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}