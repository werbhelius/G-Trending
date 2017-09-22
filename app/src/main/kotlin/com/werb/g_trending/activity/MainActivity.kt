package com.werb.g_trending.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.werb.g_trending.R
import com.werb.g_trending.adapter.TabLayoutAdapter
import com.werb.g_trending.fragment.TrendingFragment
import com.werb.g_trending.utils.Preference
import com.werb.g_trending.utils.RxEvent
import com.werb.g_trending.utils.event.LanguageDeleteEvent
import com.werb.g_trending.utils.event.LanguageEvent
import com.werb.g_trending.utils.list
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var adapter: TabLayoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initTabLayout()
        initEvent()

    }

    private fun initToolbar() {
        toolbar.title = getString(R.string.app_name)
        toolbar.inflateMenu(R.menu.toolbar)
        toolbar.setOnMenuItemClickListener(menuClickListener)
    }

    private fun initTabLayout() {
        var list = Preference.getLanguage(this)
        if (list.isEmpty()) {
            list = resources.getStringArray(R.array.trending).list()
            Preference.setLanguage(this, list)
        }
        content_viewPager.offscreenPageLimit = list.size
        adapter = TabLayoutAdapter(supportFragmentManager, list)
        content_viewPager.adapter = adapter
        tabLayout.setupWithViewPager(content_viewPager)
    }

    private fun initEvent() {
        RxEvent.toObservable(LanguageEvent::class.java)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    initTabLayout()
                })

        RxEvent.toObservable(LanguageDeleteEvent::class.java)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.removePage(content_viewPager, it.position)
                }
    }

    private val menuClickListener = Toolbar.OnMenuItemClickListener {
        when (it.itemId) {
            R.id.action_theme -> ThemeDialog().show(supportFragmentManager, "theme")
            R.id.action_language -> LanguageActivity.startActivity(this@MainActivity)
        }
        return@OnMenuItemClickListener true
    }
}
