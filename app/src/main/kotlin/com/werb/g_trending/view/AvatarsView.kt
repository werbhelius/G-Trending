package com.werb.g_trending.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.facebook.drawee.view.SimpleDraweeView
import com.werb.g_trending.R
import com.werb.g_trending.model.Repository

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
        removeAllViews()
        for (user in data) {
            addChildView(user.avatar)
        }
    }

    private fun addChildView(imageUrl: String) {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_avatar, this, false) as LinearLayout
        val avatar = layout.findViewById<SimpleDraweeView>(R.id.itemAvatar)
        avatar.setImageURI(imageUrl )
        addView(layout)
    }



}