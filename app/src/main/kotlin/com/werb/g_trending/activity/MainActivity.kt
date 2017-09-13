package com.werb.g_trending.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.werb.g_trending.R
import com.werb.g_trending.adapter.TabLayoutAdapter
import com.werb.g_trending.fragment.TrendingFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initTabLayout()

    }

    private fun initToolbar() {
        toolbar.title = getString(R.string.app_name)
        toolbar.inflateMenu(R.menu.toolbar)
        toolbar.setOnMenuItemClickListener(menuClickListener)
    }

    private fun initTabLayout() {
        val array = resources.getStringArray(R.array.trending)
        val fragments = arrayListOf<TrendingFragment>().apply {
            for (i in 1..array.size) {
                add(TrendingFragment.newInstance(array[i-1]))
            }
        }
        content_viewPager.offscreenPageLimit = array.size
        content_viewPager.adapter = TabLayoutAdapter(supportFragmentManager,fragments, array)
        tabLayout.setupWithViewPager(content_viewPager)
    }

    private val menuClickListener = Toolbar.OnMenuItemClickListener {
        when (it.itemId) {
            R.id.action_theme -> ThemeDialog().show(supportFragmentManager, "theme")
        }
        return@OnMenuItemClickListener true
    }
}
