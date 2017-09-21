package com.werb.g_trending.adapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.text.TextUtils
import android.view.View
import com.werb.g_trending.R
import com.werb.g_trending.model.Repository
import com.werb.library.MoreViewHolder
import kotlinx.android.synthetic.main.item_trending.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/21. */

class RepositoryViewHolder(containerView: View) : MoreViewHolder<Repository>(containerView) {

    private val context = containerView.context

    override fun bindData(data: Repository) {
        title.text = data.title
        description.text = data.description
        forks.text = data.forks
        starsToday.text = data.todayStars
        starsAll.text = data.stars
        languageType.text = data.language
        avatars.setData(data.users)
        if (TextUtils.isEmpty(data.color)) {
            colorType.visibility = View.GONE
        } else {
            colorType.visibility = View.VISIBLE
            data.color.let {
                val drawable = context.resources.getDrawable(R.drawable.oval_drawable)
                drawable.setColorFilter(Color.parseColor(it), PorterDuff.Mode.SRC)
                colorType.setBackgroundDrawable(drawable)
            }
        }
        if (TextUtils.isEmpty(data.todayStars)) {
            starsToday.text = "no stars today"
        }
        if (TextUtils.isEmpty(data.description)) {
            description.text = "no description"
        }
    }
}