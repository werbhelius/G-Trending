package com.werb.g_trending.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.werb.g_trending.R
import com.werb.g_trending.adapter.TrendingListAdapter
import com.werb.g_trending.bean.TrendingInfo
import kotlinx.android.synthetic.main.fragment_trending.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/6. */

class TrendingFragment() : Fragment() {

    private var data: MutableList<TrendingInfo> = ArrayList()
    private var adapter: TrendingListAdapter = TrendingListAdapter(data)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_trending, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(): TrendingFragment = TrendingFragment()
    }

}