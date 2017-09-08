package com.werb.g_trending.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.werb.g_trending.R
import com.werb.g_trending.model.ThemeModel

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/8. */

class ThemeAdapter(private val context: Context): RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder>() {

//    private fun initData(): List<ThemeModel> {
//        val themes = arrayListOf<ThemeModel>()
//        val names = context.resources.getStringArray(R.array.theme)
//        for (str in names) {
//
//        }
//    }
    
    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ThemeViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ThemeViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class ThemeViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

}