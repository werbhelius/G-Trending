package com.werb.g_trending.activity

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.werb.g_trending.R
import com.werb.g_trending.utils.Preference
import com.werb.g_trending.utils.RxEvent
import com.werb.g_trending.utils.Theme
import com.werb.g_trending.utils.event.ThemeEvent
import io.reactivex.android.schedulers.AndroidSchedulers

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/10. */

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        initTheme()
        super.onCreate(savedInstanceState)

        initEvent()
    }

    private fun initTheme(){
        val theme = Preference.getTheme(this)
        when (theme) {
            Theme.Default -> this.setTheme(R.style.DefaultTheme)
            Theme.Blue -> this.setTheme(R.style.BlueTheme)
            Theme.Indigo -> this.setTheme(R.style.IndigoTheme)
            Theme.Green -> this.setTheme(R.style.GreenTheme)
            Theme.Red -> this.setTheme(R.style.RedTheme)
            Theme.BlueGrey -> this.setTheme(R.style.BlueGreyTheme)
            Theme.Black -> this.setTheme(R.style.BlackTheme)
            Theme.Purple -> this.setTheme(R.style.PurpleTheme)
            Theme.Orange -> this.setTheme(R.style.OrangeTheme)
            Theme.Pink -> this.setTheme(R.style.PinkTheme)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (theme == Theme.Default) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }else {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            }
        }
    }

    private fun initEvent(){
        RxEvent.toObservable(ThemeEvent::class.java)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    recreate()
                })
    }

}