package com.werb.g_trending.adapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import com.werb.g_trending.R
import com.werb.g_trending.model.Language
import com.werb.library.MoreViewHolder
import kotlinx.android.synthetic.main.item_language.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/18. */

class LanguageViewHolder(containerView: View) : MoreViewHolder<Language>(containerView) {

    private val context = containerView.context

    override fun bindData(data: Language) {
        name.text = data.name
        data.color?.let {
            val drawable = containerView.context.resources.getDrawable(R.drawable.oval_drawable)
            drawable.setColorFilter(Color.parseColor(data.color), PorterDuff.Mode.SRC)
            colorType.setBackgroundDrawable(drawable)
        }
        containerView.tag = this
        addOnLongClickListener(containerView)

        delete.tag = data
        addOnClickListener(delete)
    }


}