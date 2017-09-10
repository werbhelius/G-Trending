package com.werb.g_trending.fragment

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.werb.g_trending.R
import com.werb.g_trending.adapter.TrendingListAdapter
import com.werb.g_trending.model.Repository
import com.werb.g_trending.utils.ResourcesUtils
import kotlinx.android.synthetic.main.fragment_trending.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/6. */

class TrendingFragment : Fragment() {

    private var data: MutableList<Repository> = ArrayList()
    private var adapter: TrendingListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_trending, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(ItemDecoration(context))
        adapter = TrendingListAdapter(context, data)
        recyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(): TrendingFragment = TrendingFragment()
    }

    class ItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect!!.top = ResourcesUtils.dp2px(context, 8f)
        }
    }

}