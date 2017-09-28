package com.werb.g_trending

import android.app.Application
import android.content.Context
import android.util.Log
import com.crashlytics.android.Crashlytics
import com.facebook.drawee.backends.pipeline.Fresco
import com.werb.g_trending.utils.ColorUtils
import io.fabric.sdk.android.Fabric


/** Created by wanbo <werbhelius@gmail.com> on 2017/9/10. */

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        Log.e("app", System.currentTimeMillis().toString())

        sContext = applicationContext
//        BlockCanary.install(this, AppContext()).start()
        Fresco.initialize(this)
        ColorUtils.load(applicationContext)
        Fabric.with(this, Crashlytics())
    }

    companion object {
        private var sContext: Context? = null
        fun getAppContext(): Context? {
            return sContext
        }
    }

}