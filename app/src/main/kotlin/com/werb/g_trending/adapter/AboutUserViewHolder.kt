package com.werb.g_trending.adapter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import com.werb.g_trending.activity.WebActivity
import com.werb.g_trending.model.User
import com.werb.library.MoreViewHolder
import kotlinx.android.synthetic.main.item_view_about_developer.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/27. */

class AboutUserViewHolder(containerView: View) : MoreViewHolder<User>(containerView) {

    private val context = containerView.context

    override fun bindData(data: User) {
        icon.visibility = View.GONE
        data.avatar?.let {
            icon.visibility = View.VISIBLE
            icon.setImageURI(data.avatar)
        }

        name.text = data.name
        url.text = data.url

        containerView.setOnClickListener {
            WebActivity.startActivity(context as Activity, data.url, data.name)
        }
    }
}