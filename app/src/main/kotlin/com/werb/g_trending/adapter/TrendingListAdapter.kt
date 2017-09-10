package com.werb.g_trending.adapter

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.werb.g_trending.R
import com.werb.g_trending.model.Repository
import com.werb.g_trending.view.AvatarsView
import java.util.ArrayList

/**
 * Created by liuxi on 2017/9/6.
 */
class TrendingListAdapter(private var context: Context, private var data: List<Repository>) : RecyclerView.Adapter<TrendingListAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
//        return data!!.size
        return 20
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        if (data.isNotEmpty()) {
            var info: Repository = data.get(position)
            holder!!.title!!.text = info.title
            holder!!.desc!!.text = info.description
            holder!!.forks!!.text = info.forks
            holder!!.starsToday!!.text = info.todayStars
            holder!!.starsAll!!.text = info.stars
            holder!!.language!!.text = info.language

        }
        var users: MutableList<Repository.User> = ArrayList()
        users.add(Repository.User("1", "https://b-ssl.duitang.com/uploads/item/201603/02/20160302195311_dE35x.thumb.700_0.jpeg"))
        users.add(Repository.User("2", "https://b-ssl.duitang.com/uploads/item/201512/05/20151205201203_ZUatA.thumb.700_0.jpeg"))
        users.add(Repository.User("3", "https://b-ssl.duitang.com/uploads/item/201511/12/20151112201742_zuiRH.thumb.700_0.jpeg"))
        holder!!.avatars!!.setData(users)
        Log.d("test","${users.size}hahahahahahahha")
        holder!!.avatars!!.measure(0,0)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_trending_list, null))
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var title: TextView? = null
        var desc: TextView? = null
        var language: TextView? = null
        var avatars: AvatarsView? = null
        var starsToday: TextView? = null
        var starsAll: TextView? = null
        var forks: TextView? = null

        init {
            title = itemView!!.findViewById(R.id.title)
            desc = itemView.findViewById(R.id.description)
            language = itemView.findViewById(R.id.languageType)
            avatars = itemView.findViewById(R.id.avatars)
            starsToday = itemView.findViewById(R.id.starsToday)
            starsAll = itemView.findViewById(R.id.starsAll)
            forks = itemView.findViewById(R.id.forks)
        }

    }
}