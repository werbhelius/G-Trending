package com.werb.g_trending.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.werb.g_trending.R
import com.werb.g_trending.adapter.ChooseViewHolder
import com.werb.g_trending.utils.ColorUtils
import com.werb.g_trending.utils.Preference
import com.werb.library.MoreAdapter
import com.werb.library.link.RegisterItem
import kotlinx.android.synthetic.main.activity_choose.*

/** Created by wanbo <werbhelius@gmail.com> on 2017/9/23. */

class ChooseActivity : BaseActivity() {

    private val adapter: MoreAdapter by lazy { MoreAdapter() }
    private var handler = Handler(Looper.getMainLooper())
    private val languages = ColorUtils.colors.colors
    private lateinit var saveLanguages: MutableList<String>
    private var keyChat: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener { finish() }
        search.addTextChangedListener(watcher)

        saveLanguages = Preference.getLanguage(this)

        adapter.apply {
            register(RegisterItem(R.layout.item_view_choose, ChooseViewHolder::class.java))
            attachTo(recyclerView)
        }

        loadData()
    }

    private fun loadData(){
        adapter.removeAllData()
        languages.keys
                .map {
                    val name = it
                    languages[it]?.let {
                        it.name = name
                        it
                    }
                }
                .forEach {
                    it?.let {
                        it.check = saveLanguages.contains(it.name)
                        adapter.loadData(it)
                    }
                }
    }

    private val watcher = object: TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
            keyChat = p0.toString()
            if (TextUtils.isEmpty(keyChat)){
                loadData()
            }else {
                updateList()
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    }

    private fun updateList() {
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable, 750)
    }

    private var runnable: Runnable = Runnable {
        adapter.removeAllData()
        languages.keys
                .map {
                    val name = it
                    languages[it]?.let {
                        it.name = name
                        it
                    }
                }
                .forEach {
                    it?.let {
                        if (it.name.toUpperCase().contains(keyChat.toUpperCase())) {
                            it.check = saveLanguages.contains(it.name)
                            adapter.loadData(it)
                        }
                    }
                }
    }


    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_anim_not_change, R.anim.activity_anim_current_to_right)
    }

    companion object {
        fun startActivity(activity: Activity) {
            val intent = Intent(activity, ChooseActivity::class.java)
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.activity_anim_right_to_current, R.anim.activity_anim_not_change)
        }
    }
}