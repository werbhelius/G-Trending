package com.werb.g_trending.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.facebook.drawee.view.SimpleDraweeView
import com.werb.g_trending.R
import com.werb.g_trending.model.Repository
import com.werb.g_trending.utils.ResourcesUtils

/**
 *
 * 用户图标列表
 * Created by liuxi on 2017/9/7.
 */
class AvatarsView : LinearLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    init {
        orientation = LinearLayout.HORIZONTAL
    }

    fun setData(data: MutableList<Repository.User>) {
        for (user in data) {
            addChildView(user.avatar)
        }
    }

    private fun addChildView(imageUrl: String) {
        var childImage = LayoutInflater.from(context).inflate(R.layout.item_avatar, null) as SimpleDraweeView
        childImage.setImageURI(imageUrl)
        var lp: LinearLayout.LayoutParams = LinearLayout.LayoutParams(-2, -2)
        lp.width = ResourcesUtils.dp2px(context, 20f)
        lp.height = ResourcesUtils.dp2px(context, 20f)
        lp.leftMargin = ResourcesUtils.dp2px(context, 3f)
        addView(childImage,lp)
    }



}