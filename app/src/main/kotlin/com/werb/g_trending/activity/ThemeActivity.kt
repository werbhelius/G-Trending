package com.werb.g_trending.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.werb.g_trending.R
import com.werb.g_trending.adapter.ThemeAdapter
import kotlinx.android.synthetic.main.activity_theme.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/8. */

class ThemeActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)

        toolbar.title = getString(R.string.menu_theme)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        recyclerView.adapter = ThemeAdapter(this)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_anim_not_change, R.anim. activity_anim_current_to_right)
    }

    companion object {
        fun startActivity(activity: Activity) {
            val intent = Intent(activity, ThemeActivity::class.java)
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.activity_anim_right_to_current, R.anim.activity_anim_not_change)
        }
    }
}