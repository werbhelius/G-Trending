package com.werb.g_trending

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.werb.g_trending.api.TrendingRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        initTabLayout()

        Thread{
            TrendingRequest.loadData(null)
        }.start()
    }

    private fun initToolbar() {
        toolbar.title = getString(R.string.app_name)
        toolbar.inflateMenu(R.menu.toolbar)
    }

    private fun initTabLayout() {
        val fragments = arrayListOf<Fragment>().apply {
            for (i in 1..10){
                add(TrendingFrgment.newInstance())
            }
        }
        content_viewPager.offscreenPageLimit = 5
        content_viewPager.adapter = TabLayoutAdapter(supportFragmentManager, fragments, resources.getStringArray(R.array.trending))
        tabLayout.setupWithViewPager(content_viewPager)
    }
}
