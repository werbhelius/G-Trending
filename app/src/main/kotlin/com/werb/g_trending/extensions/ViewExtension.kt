package com.werb.g_trending.extensions

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.res.Configuration
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import android.view.animation.AccelerateInterpolator


fun View.dp2px(dpValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

val View.density: Int
    get() {
        val density = context.resources.displayMetrics.density
        return (density + 0.5f).toInt()
    }

fun View.px2dp(pxValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

val View.isLowDesity: Boolean
    get() = context.resources.displayMetrics.widthPixels < 1080

val View.widthPixels: Int
    get() {
        val displayMetrics = context.resources.displayMetrics
        val cf = context.resources.configuration
        val ori = cf.orientation
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            return displayMetrics.heightPixels
        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {
            return displayMetrics.widthPixels
        }
        return 0
    }

val View.heightPixels: Int
    get() {
        val displayMetrics = context.resources.displayMetrics
        val cf = context.resources.configuration
        val ori = cf.orientation
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            return displayMetrics.widthPixels
        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {
            return displayMetrics.heightPixels
        }
        return 0
    }

fun View.getDimen(resId: Int): Int {
    return context.resources.getDimensionPixelSize(resId)
}

fun View.getMeasureWidth(): Int {
    val w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    val h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    measure(w, h)
    return measuredWidth
}

fun View.getMeasureHeight(): Int {
    val w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    val h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    measure(w, h)
    return measuredHeight
}

fun View.createDropAnim(start: Int, end: Int, duration: Long, animEnd: () -> Unit): ValueAnimator {
    val va = ValueAnimator.ofInt(start, end)
    va.interpolator = AccelerateInterpolator()
    val max = Math.max(start, end)
    va.addUpdateListener { animation ->
        val value = animation.animatedValue as Int
        layoutParams.height = value
        layoutParams = layoutParams
    }
    va.duration = duration
    va.addListener(object: Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {}

        override fun onAnimationCancel(p0: Animator?) {}

        override fun onAnimationStart(p0: Animator?) {}

        override fun onAnimationEnd(p0: Animator?) {
            animEnd()
        }

    })
    return va
}

