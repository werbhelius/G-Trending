package com.werb.g_trending.adapter

import android.view.View
import com.werb.library.MoreViewHolder
import kotlinx.android.synthetic.main.item_view_about_title.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/27. */

class AboutInfoViewHolder(containerView: View) : MoreViewHolder<String>(containerView) {

    override fun bindData(data: String) {
        title.text = data
    }
}