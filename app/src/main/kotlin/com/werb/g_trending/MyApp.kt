package com.werb.g_trending

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import com.github.moduth.blockcanary.BlockCanary
import com.werb.g_trending.utils.ColorUtils


/** Created by wanbo <werbhelius@gmail.com> on 2017/9/10. */

class MyApp: Application() {


    override fun onCreate() {
        super.onCreate()
        sContext = applicationContext
//        BlockCanary.install(this, AppContext()).start()
        Fresco.initialize(this)
        ColorUtils.load(applicationContext)
    }

    companion object {
        private var sContext: Context? = null
        fun getAppContext(): Context? {
            return sContext
        }
    }

}