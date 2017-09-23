package com.werb.g_trending.adapter

import android.view.View
import com.werb.eventbus.EventBus
import com.werb.g_trending.model.Language
import com.werb.g_trending.utils.event.LanguageEvent
import com.werb.library.MoreViewHolder
import kotlinx.android.synthetic.main.item_view_choose.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/23. */

class ChooseViewHolder(containerView: View) : MoreViewHolder<Language>(containerView) {

    override fun bindData(data: Language) {
        name.text = data.name
        check.isChecked = data.check

        check.setOnClickListener {
            if (check.isChecked) {
                EventBus.post(LanguageEvent(data), "add")
            } else {
                EventBus.post(LanguageEvent(data), "delete")
            }
        }
    }
}