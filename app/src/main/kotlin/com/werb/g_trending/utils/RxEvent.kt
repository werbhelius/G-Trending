package com.werb.g_trending.utils

import com.werb.g_trending.utils.event.IEvent
import io.reactivex.subjects.PublishSubject
import io.reactivex.Observable


/**
 * Created by wanbo on 2017/6/27.
 */
object RxEvent {

    private val event = PublishSubject.create<Any>().toSerialized()

    fun send(any: IEvent) {
        event.onNext(any)
    }

    fun <IEvent> toObservable(eventType: Class<IEvent>): Observable<IEvent> {
        return event.ofType(eventType)
    }

}