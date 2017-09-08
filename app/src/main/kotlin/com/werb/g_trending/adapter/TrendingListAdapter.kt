package com.werb.g_trending.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.werb.g_trending.R
import com.werb.g_trending.bean.TrendingInfo
import com.werb.g_trending.view.UserIconsView

/**
 * Created by liuxi on 2017/9/6.
 */
class TrendingListAdapter(private var data: List<TrendingInfo>) : RecyclerView.Adapter<TrendingListAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
//        return data!!.size
        return 20
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        if (data.isNotEmpty()) {
            var info: TrendingInfo = data.get(position)
            holder!!.headerInfo!!.text = info.owner + "/" + info.repository
            holder!!.desc!!.text = info.desc
            holder.languageLogo!!.text = info.languageType
            holder.userIconList!!.setData(info.iconUrlList)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_trending_list, null))
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var headerInfo: TextView? = null
        var desc: TextView? = null
        var languageLogo: TextView? = null
        var userIconList: UserIconsView? = null

        init {
            headerInfo = itemView!!.findViewById(R.id.header)
            desc = itemView.findViewById(R.id.desc)
            languageLogo = itemView.findViewById(R.id.languageType)
            userIconList = itemView.findViewById(R.id.userIconList)
        }

        override fun toString(): String {
            return "ViewHolder(headerInfo=$headerInfo, desc=$desc, languageLogo=$languageLogo, userIconList=$userIconList)"
        }

    }
}