package com.werb.g_trending.activity

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.werb.g_trending.R
import android.view.LayoutInflater



/** Created by wanbo <werbhelius@gmail.com> on 2017/9/12. */

class ThemeDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialog = AlertDialog.Builder(context, R.style.Dialog_Style).create()
        val contentView = LayoutInflater.from(context).inflate(
                R.layout.widget_view_theme, null)
        alertDialog.setContentView(contentView)
        return alertDialog
    }

}