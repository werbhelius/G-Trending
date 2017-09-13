package com.werb.g_trending.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.werb.g_trending.R


/** Created by wanbo <werbhelius@gmail.com> on 2017/9/10. */

class CircleView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paint: Paint by lazy { Paint() }
    private val paintBorder: Paint by lazy { Paint() }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var board = width.toFloat()
        if (width != height) {
            val min = Math.min(width, height)
            board = min.toFloat()
        }

        paint.isAntiAlias = true
        paint.strokeWidth = 10f
        paint.style = Paint.Style.FILL
        canvas.drawCircle(board / 2, board / 2, board / 2 - .5f, paint)

        paintBorder.isAntiAlias = true
        paintBorder.strokeWidth = 2f
        paintBorder.style = Paint.Style.FILL
        paintBorder.color = resources.getColor(R.color.colorWhite)
        canvas.drawCircle(board / 2, board / 2 , 15f, paintBorder)

    }

    fun setColor(color: Int) {
        if (color == -1){
            paint.color = resources.getColor(R.color.colorAccent)
        }else {
            paint.color = color
        }
        invalidate()
    }
}