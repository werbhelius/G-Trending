package com.werb.g_trending.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.werb.g_trending.R
import com.werb.g_trending.model.Repository
import com.werb.g_trending.view.AvatarsView
import com.werb.g_trending.view.CircleView
import java.util.ArrayList

/**
 * Created by liuxi on 2017/9/6.
 */
class TrendingListAdapter(private var context: Context) : RecyclerView.Adapter<TrendingListAdapter.ViewHolder>() {

    var data: MutableList<Repository> = mutableListOf()
        private set

    override fun getItemCount(): Int {
        return data.size
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    fun addItem(repos: List<Repository>) {
        repos.forEach {
            data.add(it)
            notifyItemInserted(data.size - 1)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (data.isNotEmpty()) {
            val info: Repository = data[position]
            holder.title?.text = info.title
            holder.desc?.text = info.description
            holder.forks?.text = info.forks
            holder.starsToday?.text = info.todayStars
            holder.starsAll?.text = info.stars
            holder.language?.text = info.language
            holder.avatars?.setData(info.users)
            if (TextUtils.isEmpty(info.color)) {
                holder.colorType?.visibility = View.GONE
            } else {
                holder.colorType?.visibility = View.VISIBLE
                info.color?.let {
                    val drawable = context.resources.getDrawable(R.drawable.oval_drawable)
                    drawable.setColorFilter(Color.parseColor(it), PorterDuff.Mode.SRC)
                    holder.colorType?.setBackgroundDrawable(drawable)
                }
            }
            if (TextUtils.isEmpty(info.todayStars)) {
                holder.starsToday?.text = "no stars today"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_trending, parent, false))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView? = null
        var desc: TextView? = null
        var language: TextView? = null
        var avatars: AvatarsView? = null
        var starsToday: TextView? = null
        var starsAll: TextView? = null
        var forks: TextView? = null
        var colorType: AppCompatImageView? = null

        init {
            title = itemView.findViewById(R.id.title)
            desc = itemView.findViewById(R.id.description)
            language = itemView.findViewById(R.id.languageType)
            avatars = itemView.findViewById(R.id.avatars)
            starsToday = itemView.findViewById(R.id.starsToday)
            starsAll = itemView.findViewById(R.id.starsAll)
            forks = itemView.findViewById(R.id.forks)
            colorType = itemView.findViewById(R.id.colorType)
        }

    }
}