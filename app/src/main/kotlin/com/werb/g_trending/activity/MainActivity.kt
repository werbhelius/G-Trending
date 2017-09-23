package com.werb.g_trending.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.werb.eventbus.EventBus
import com.werb.eventbus.Subscriber
import com.werb.g_trending.R
import com.werb.g_trending.adapter.TabLayoutAdapter
import com.werb.g_trending.utils.Preference
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
    }

    override fun onStart() {
        super.onStart()
        EventBus.register(this)
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

    @Subscriber(tag = "move")
    private fun languageMove(event: LanguageEvent){
        initTabLayout()
    }

    @Subscriber(tag = "add")
    private fun languageAdd(event: LanguageEvent){
        event.language?.let {
            adapter.addPage(content_viewPager, it)
        }
    }

    @Subscriber(tag = "delete")
    private fun languageDelete(event: LanguageEvent){
        event.language?.let {
            adapter.removePage(content_viewPager, it)
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
