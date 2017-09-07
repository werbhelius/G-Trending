package com.werb.g_trending.view

import android.content.Context
import android.widget.LinearLayout
import com.facebook.drawee.view.SimpleDraweeView
import com.werb.g_trending.utils.ResourcesUtils

/**
 *
 * 用户图标列表
 * Created by liuxi on 2017/9/7.
 */
class UserIconsView(context: Context?) : LinearLayout(context) {
    init {
        orientation = LinearLayout.HORIZONTAL
    }

    fun setData(data: List<String>) {
        for (str in data) {
            addChildView(str)
        }
    }

    private fun addChildView(imageUrl: String) {
        var childImage: SimpleDraweeView = SimpleDraweeView(context)
        childImage.setImageURI(imageUrl)
        var lp: LinearLayout.LayoutParams = LinearLayout.LayoutParams(-2, -2)
        lp.width = ResourcesUtils.dp2px(context, 20f)
        lp.height = ResourcesUtils.dp2px(context, 20f)
        lp.leftMargin = ResourcesUtils.dp2px(context, 5f)
        childImage.layoutParams = lp
        addView(childImage)
    }

}