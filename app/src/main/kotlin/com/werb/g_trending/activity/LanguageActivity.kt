package com.werb.g_trending.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.werb.g_trending.R
import com.werb.g_trending.adapter.LanguageViewHolder
import com.werb.g_trending.utils.ColorUtils
import com.werb.g_trending.utils.Preference
import com.werb.library.MoreAdapter
import com.werb.library.action.MoreClickListener
import com.werb.library.link.RegisterItem
import kotlinx.android.synthetic.main.activity_language.*
import java.util.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/15. */

class LanguageActivity : BaseActivity() {

    private val adapter: MoreAdapter by lazy { MoreAdapter() }
    private var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        toolbar.title = getString(R.string.menu_language)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }
        itemTouchHelper.attachToRecyclerView(recyclerView)
        adapter.apply {
            register(RegisterItem(R.layout.item_language, LanguageViewHolder::class.java, itemClickListener))
            attachTo(recyclerView)
        }

        val languages = Preference.getLanguage(this)
        languages.forEach {
            val language = ColorUtils.getLanguage(it)
            language?.let {
                adapter.loadData(it)
            }
        }
    }

    private val itemClickListener = object: MoreClickListener(){
        override fun onItemLongClick(view: View, position: Int): Boolean {
            val holder =  view.tag as LanguageViewHolder
            itemTouchHelper.startDrag(holder)
            return true
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {

        override fun isLongPressDragEnabled(): Boolean {
            return false
        }

        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            return makeMovementFlags(dragFlags, 0)
        }

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            val fromPosition = viewHolder.adapterPosition
            val toPosition = target.adapterPosition
            if (fromPosition < toPosition) {
                for (i in fromPosition until toPosition) {
                    Collections.swap(adapter.list, i, i + 1)
                }
            } else {
                for (i in fromPosition downTo toPosition + 1) {
                    Collections.swap(adapter.list, i, i - 1)
                }
            }
            adapter.notifyItemMoved(fromPosition, toPosition)
            updateList()
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
    })

    private fun updateList() {
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable, 750)
    }

    private var runnable: Runnable = Runnable { }


    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_anim_not_change, R.anim.activity_anim_current_to_right)
    }

    companion object {
        fun startActivity(activity: Activity) {
            val intent = Intent(activity, LanguageActivity::class.java)
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.activity_anim_right_to_current, R.anim.activity_anim_not_change)
        }
    }

}