package com.werb.g_trending.fragment

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.werb.g_trending.R
import com.werb.g_trending.adapter.TrendingListAdapter
import com.werb.g_trending.api.TrendingRequest
import com.werb.g_trending.model.Repository
import com.werb.g_trending.utils.ResourcesUtils
import kotlinx.android.synthetic.main.fragment_trending.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/6. */

class TrendingFragment : Fragment() {

    private val data: MutableList<Repository> by lazy { mutableListOf<Repository>() }
    private val adapter: TrendingListAdapter by lazy { TrendingListAdapter(context, data) }
    private var language = ""
    private var first = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        language = arguments.getString(ARG_LANGUAGE)
        return inflater?.inflate(R.layout.fragment_trending, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.addItemDecoration(ItemDecoration(context))
        recyclerView.adapter = adapter
        refresh.setOnRefreshListener {
            request()
        }

        if (first) refresh.isRefreshing = true
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser){
            request()
        }
    }

    private fun request() {
        TrendingRequest.repository(language)
                .doFinally {
                    refresh.isRefreshing = false
                    first = false
                }
                .subscribe {
                    data.clear()
                    data.addAll(it)
                    adapter.notifyDataSetChanged()
                }
    }

    companion object {
        private const val ARG_LANGUAGE = "LANGUAGE"

        fun newInstance(name: String): TrendingFragment {
            return TrendingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LANGUAGE, name)
                }
            }
        }

    }

    private inner class ItemDecoration(private val context: Context) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect?.top = ResourcesUtils.dp2px(context, 3f)
        }
    }

}