package com.werb.g_trending.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.werb.g_trending.fragment.TrendingFragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import com.werb.g_trending.utils.Preference

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/6. */

class TabLayoutAdapter(fm: FragmentManager, private val titles: MutableList<String>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = TrendingFragment.newInstance(titles[position])

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence = titles[position]

    override fun getItemPosition(`object`: Any?): Int {
        return PagerAdapter.POSITION_NONE
    }

    fun removePage(pager: ViewPager, position: Int) {
        pager.removeViewAt(position)
        titles.removeAt(position)
        notifyDataSetChanged()
        pager.offscreenPageLimit = titles.size
        Preference.setLanguage(pager.context, titles)
    }

}