package com.werb.g_trending.activity

import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import com.werb.eventbus.EventBus
import com.werb.eventbus.Subscriber
import com.werb.g_trending.Config
import com.werb.g_trending.R
import com.werb.g_trending.adapter.TabLayoutAdapter
import com.werb.g_trending.utils.Preference
import com.werb.g_trending.utils.event.LanguageEvent
import com.werb.g_trending.utils.list
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private lateinit var adapter: TabLayoutAdapter
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(android.R.color.transparent)
        }

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
        mainDrawer.setNavigationItemSelectedListener(drawerItemClick)
        mainDrawer.menu.getItem(0).isChecked = true
        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(this, mainDrawerLayout, toolbar, R.string.open, R.string.close)
        mainDrawerLayout.setDrawerListener(toggle)
        toggle.syncState()
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
    private fun languageMove(event: LanguageEvent) {
        initTabLayout()
    }

    @Subscriber(tag = "add")
    private fun languageAdd(event: LanguageEvent) {
        event.language?.let {
            adapter.addPage(content_viewPager, it)
        }
    }

    @Subscriber(tag = "delete")
    private fun languageDelete(event: LanguageEvent) {
        event.language?.let {
            adapter.removePage(content_viewPager, it)
        }
    }

    private val drawerItemClick = NavigationView.OnNavigationItemSelectedListener {

        when (it.itemId) {
            R.id.repos -> {
                if (Config.TRENDING_TAG != "repos") {
                    Config.repos()
                    initTabLayout()
                }
                mainDrawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.developer -> {
                if (Config.TRENDING_TAG != "developer") {
                    Config.developer()
                    initTabLayout()
                }
                mainDrawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.language -> {
                LanguageActivity.startActivity(this@MainActivity)
            }
            R.id.theme -> {
                ThemeDialog().show(supportFragmentManager, "theme")
            }
            R.id.info -> {

            }
        }

        return@OnNavigationItemSelectedListener true
    }
}
