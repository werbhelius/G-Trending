package com.werb.g_trending.fragment

import android.support.v4.app.Fragment
import android.os.Bundle



/** Created by wanbo <werbhelius@gmail.com> on 2017/9/13. */

abstract class LazyLoadFragment: Fragment() {

    private var isViewInitiated: Boolean = false
    private var isDataLoaded: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewInitiated = true
        prepareRequestData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        prepareRequestData()
    }

    abstract fun requestData()

    private fun prepareRequestData(): Boolean {
        return prepareRequestData(false)
    }

    private fun prepareRequestData(forceUpdate: Boolean): Boolean {
        if (userVisibleHint && isViewInitiated && (!isDataLoaded || forceUpdate)) {
            requestData()
            isDataLoaded = true
            return true
        }
        return false
    }

}