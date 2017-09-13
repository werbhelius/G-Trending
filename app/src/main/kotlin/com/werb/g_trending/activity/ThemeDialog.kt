package com.werb.g_trending.activity

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import com.werb.g_trending.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.werb.g_trending.adapter.ThemeAdapter


/** Created by wanbo <werbhelius@gmail.com> on 2017/9/12. */

class ThemeDialog: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.setCanceledOnTouchOutside(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.widget_view_theme, null)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ThemeAdapter(context, this)
        return AlertDialog.Builder(context)
                .setView(view)
                .create()
    }

}