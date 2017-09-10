package com.werb.g_trending

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/10. */

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}