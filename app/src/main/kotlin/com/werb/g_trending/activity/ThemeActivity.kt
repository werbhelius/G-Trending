package com.werb.g_trending.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.werb.g_trending.R
import kotlinx.android.synthetic.main.activity_theme.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/8. */

class ThemeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)

        toolbar.title = getString(R.string.menu_theme)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.svg_arrow_back)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_anim_not_change, R.anim.activity_anim_top_to_bottom)
    }

    companion object {
        fun startActivity(activity: Activity) {
            val intent = Intent(activity, ThemeActivity::class.java)
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.activity_anim_bottom_to_top, R.anim.activity_anim_not_change)
        }
    }
}