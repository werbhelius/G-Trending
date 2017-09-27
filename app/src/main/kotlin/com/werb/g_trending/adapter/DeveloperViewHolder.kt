package com.werb.g_trending.adapter

import android.content.Intent
import android.net.Uri
import android.view.View
import com.werb.g_trending.model.Developer
import com.werb.library.MoreViewHolder
import kotlinx.android.synthetic.main.item_view_developer.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/25. */

class DeveloperViewHolder(containerView: View) : MoreViewHolder<Developer>(containerView) {

    override fun bindData(data: Developer) {
        icon.setImageURI(data.avatar)
        name.text = data.name
        repos.text = data.repositoryName
        desc.text = data.repositoryDesc

        containerView.setOnClickListener {
            val intent = Intent()
            intent.action = "android.intent.action.VIEW"
            intent.data = Uri.parse("https://github.com/" + data.url)
            itemView.context.startActivity(intent)
        }
    }
}