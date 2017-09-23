package com.werb.g_trending.extensions

import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/5. */

/** Toolbar icon 切换 */
fun ActionBarDrawerToggle.switch(position: Float) {
    if (position == 1f) {
        drawerArrowDrawable.setVerticalMirror(true)
    } else if (position == 0f) {
        drawerArrowDrawable.setVerticalMirror(false)
    }
    drawerArrowDrawable.progress = position
}
