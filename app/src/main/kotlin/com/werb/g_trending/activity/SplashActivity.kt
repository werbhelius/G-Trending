package com.werb.g_trending.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log


/** Created by wanbo <werbhelius@gmail.com> on 2017/9/28. */

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("SplashActivity", System.currentTimeMillis().toString())
        Handler().postDelayed({ MainActivity.startActivity(this) }, 300)

    }


}