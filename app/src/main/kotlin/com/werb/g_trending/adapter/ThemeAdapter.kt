package com.werb.g_trending.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.werb.g_trending.R
import com.werb.g_trending.model.ThemeModel
import com.werb.g_trending.utils.Preference
import com.werb.g_trending.utils.RxEvent
import com.werb.g_trending.utils.Theme
import com.werb.g_trending.utils.event.ThemeEvent
import kotlinx.android.synthetic.main.item_theme.view.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/8. */

class ThemeAdapter(private val context: Context) : RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder>() {

    private val list: List<ThemeModel> by lazy { initData() }

    private fun initData(): List<ThemeModel> {
        val themes = arrayListOf<ThemeModel>()
        val names = context.resources.getStringArray(R.array.theme)
        val colors = context.resources.getIntArray(R.array.theme_color)
        return names.mapTo(themes) {
            ThemeModel(it, colors[names.indexOf(it)]).apply {
                select = it == Preference.getTheme(context).name
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ThemeViewHolder {
        return ThemeViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_theme, parent, false))
    }

    override fun onBindViewHolder(holder: ThemeViewHolder?, position: Int) {
        if (holder is ThemeViewHolder) {
            holder.bindData(list[position])
        }
    }

    inner class ThemeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val color = itemView.color
        private val str = itemView.str
        private val select = itemView.select

        fun bindData(themeModel: ThemeModel) {
            color.setColor(themeModel.color)
            str.text = themeModel.name
            select.isChecked = themeModel.select

            select.setOnClickListener {
                list.forEach {
                    it.select = list.indexOf(it) == layoutPosition
                }
                notifyDataSetChanged()

                Preference.setTheme(context, Theme.valueOf(themeModel.name))
                RxEvent.send(ThemeEvent())
            }
        }

    }

}