package com.werb.g_trending.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/6. */

class TabLayoutAdapter(fm: FragmentManager, private val fragments: List<Fragment>, private val titles: Array<String>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence = titles[position]
}