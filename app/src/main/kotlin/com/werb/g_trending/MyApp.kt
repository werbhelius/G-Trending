package com.werb.g_trending

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.squareup.leakcanary.LeakCanary

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/10. */

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)

        Fresco.initialize(this)
    }
}