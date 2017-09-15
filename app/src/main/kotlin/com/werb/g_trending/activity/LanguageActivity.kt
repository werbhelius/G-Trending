package com.werb.g_trending.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.werb.g_trending.R
import kotlinx.android.synthetic.main.activity_language.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/15. */

class LanguageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        toolbar.title = getString(R.string.menu_language)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_anim_not_change, R.anim.activity_anim_current_to_right)
    }

    companion object {
        fun startActivity(activity: Activity) {
            val intent = Intent(activity, LanguageActivity::class.java)
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.activity_anim_right_to_current, R.anim.activity_anim_not_change)
        }
    }

}