package com.werb.g_trending.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.werb.g_trending.R
import com.werb.g_trending.adapter.AboutHeaderViewholder
import com.werb.library.MoreAdapter
import com.werb.library.link.RegisterItem
import kotlinx.android.synthetic.main.activity_about.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/27. */

class AboutActivity: BaseActivity() {

    private val adapter: MoreAdapter by lazy { MoreAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener { finish() }

        adapter.apply {
            register(RegisterItem(R.layout.item_view_about_header, AboutHeaderViewholder::class.java))
            attachTo(recyclerView)
        }

        adapter.loadData("About Header")
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_anim_not_change, R.anim.activity_anim_current_to_right)
    }

    companion object {
        fun startActivity(activity: Activity) {
            val intent = Intent(activity, AboutActivity::class.java)
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.activity_anim_right_to_current, R.anim.activity_anim_not_change)
        }
    }

}