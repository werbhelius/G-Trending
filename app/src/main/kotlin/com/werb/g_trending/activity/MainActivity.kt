package com.werb.g_trending.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import com.werb.g_trending.R
import com.werb.g_trending.adapter.TabLayoutAdapter
import com.werb.g_trending.fragment.TrendingFragment
import com.werb.g_trending.api.TrendingRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        initTabLayout()

        TrendingRequest.developer("").subscribe {
            println(it.size)
        }
    }

    private fun initToolbar() {
        toolbar.title = getString(R.string.app_name)
        toolbar.inflateMenu(R.menu.toolbar)
        toolbar.setOnMenuItemClickListener(menuClickListener)
    }

    private fun initTabLayout() {
        val fragments = arrayListOf<Fragment>().apply {
            for (i in 1..10){
                add(TrendingFragment.newInstance())
            }
        }
        content_viewPager.offscreenPageLimit = 5
        content_viewPager.adapter = TabLayoutAdapter(supportFragmentManager, fragments, resources.getStringArray(R.array.trending))
        tabLayout.setupWithViewPager(content_viewPager)
    }

    private val menuClickListener = Toolbar.OnMenuItemClickListener {
        when (it.itemId){
            R.id.action_theme -> ThemeActivity.startActivity(this@MainActivity)
        }
        return@OnMenuItemClickListener true
    }
}
