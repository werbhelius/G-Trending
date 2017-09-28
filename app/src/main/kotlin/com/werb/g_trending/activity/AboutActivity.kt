package com.werb.g_trending.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.werb.g_trending.R
import com.werb.g_trending.adapter.AboutInfoViewHolder
import com.werb.g_trending.adapter.AboutUserViewHolder
import com.werb.g_trending.model.User
import com.werb.library.MoreAdapter
import com.werb.library.link.MultiLink
import com.werb.library.link.RegisterItem
import kotlinx.android.synthetic.main.activity_about.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/27. */

class AboutActivity : BaseActivity() {

    private val adapter: MoreAdapter by lazy { MoreAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener { finish() }

        adapter.apply {
            register(RegisterItem(R.layout.item_view_about_developer, AboutUserViewHolder::class.java))
            register(RegisterItem(R.layout.item_view_about_title, AboutInfoViewHolder::class.java))
            attachTo(recyclerView)
        }

        adapter.loadData("Developer & Designer")
        adapter.loadData(User("wanbo", "https://avatars1.githubusercontent.com/u/12763277?v=4&s=460", "https://github.com/Werb"))
        adapter.loadData(User("liuxi", "https://avatars2.githubusercontent.com/u/26291475?v=4&s=460", "https://github.com/LiuXi0314"))
        adapter.loadData("Open source")
        adapter.loadData(User("Werb/MoreType", null, url = "https://github.com/Werb/MoreType"))
        adapter.loadData(User("Werb/EventBusKotlin", null, url = "https://github.com/Werb/EventBusKotlin"))
        adapter.loadData(User("jhy/jsoup", null, url = "https://github.com/jhy/jsoup"))
        adapter.loadData(User("facebook/fresco", null, url = "https://github.com/facebook/fresco"))
        adapter.loadData(User("ReactiveX/RxJava", null, url = "https://github.com/ReactiveX/RxJava"))
        adapter.loadData(User("ReactiveX/RxAndroid", null, url = "https://github.com/ReactiveX/RxAndroid"))
        adapter.loadData(User("square/retrofit", null, url = "https://github.com/square/retrofit"))
        adapter.loadData(User("markzhai/AndroidPerformanceMonitor", null, url = "https://github.com/markzhai/AndroidPerformanceMonitor"))
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