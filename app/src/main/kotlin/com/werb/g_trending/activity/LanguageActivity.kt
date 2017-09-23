package com.werb.g_trending.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.design.widget.Snackbar
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import com.werb.eventbus.EventBus
import com.werb.eventbus.Subscriber
import com.werb.g_trending.R
import com.werb.g_trending.adapter.LanguageViewHolder
import com.werb.g_trending.model.Language
import com.werb.g_trending.utils.ColorUtils
import com.werb.g_trending.utils.Preference
import com.werb.g_trending.utils.event.LanguageEvent
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

        val drawable = resources.getDrawable(R.drawable.ic_add_black_24dp)
        val typedValue = TypedValue()
        theme.resolveAttribute(R.attr.colorPrimaryLight, typedValue, true)
        val wrappedDrawable = DrawableCompat.wrap(drawable)
        DrawableCompat.setTint(wrappedDrawable, resources.getColor(typedValue.resourceId))
        add.setBackgroundDrawable(wrappedDrawable)
        add.setOnClickListener { add() }

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

    override fun onStart() {
        super.onStart()
        EventBus.register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.unRegister(this)
    }

    private fun add() {
        ChooseActivity.startActivity(this)
        Snackbar.make(recyclerView, getString(R.string.size_down_ten), Snackbar.LENGTH_SHORT)
                .setAction("OK", {}).show()
    }

    @Subscriber(tag = "add")
    private fun languageAdd(event: LanguageEvent) {
        event.language?.let {
            adapter.loadData(it)
        }
    }

    @Subscriber(tag = "delete")
    private fun languageDelete(event: LanguageEvent) {
        event.language?.let {
            adapter.removeData(it)
        }
    }

    private val itemClickListener = object : MoreClickListener() {

        override fun onItemClick(view: View, position: Int) {
            val language = view.tag as Language
            val alertDialog = AlertDialog.Builder(this@LanguageActivity)
            alertDialog.setMessage(R.string.delete_language)
            alertDialog.setPositiveButton(R.string.delete, { _, _ ->
                if (adapter.itemCount > 1) {
                    adapter.removeData(position)
                    EventBus.post(LanguageEvent(language), "delete")
                } else {
                    Toast.makeText(this@LanguageActivity, getString(R.string.size_up_one), Toast.LENGTH_SHORT).show()
                }
            })
            alertDialog.setNegativeButton(R.string.cancel, { dialog, _ -> dialog.dismiss() })
            alertDialog.show()
        }

        override fun onItemLongClick(view: View, position: Int): Boolean {
            val holder = view.tag as LanguageViewHolder
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

    private var runnable: Runnable = Runnable {
        val languages = arrayListOf<String>()
        adapter.list.forEach {
            if (it is Language) {
                languages.add(it.name)
            }
        }
        if (languages.isNotEmpty()) {
            Preference.setLanguage(this, languages)
            EventBus.post(LanguageEvent(null), "move")
        }
    }


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