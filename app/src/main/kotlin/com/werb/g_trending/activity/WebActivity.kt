package com.werb.g_trending.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import com.werb.g_trending.R
import kotlinx.android.synthetic.main.activity_web.*
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * Created by wanbo on 2018/3/13.
 */
class WebActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener { finish() }
        toolbar.title = title
        refresh.setColorSchemeResources(R.color.refresh_progress_1,
            R.color.refresh_progress_2, R.color.refresh_progress_3);

        setWebView(url)
    }

    private fun setWebView(url: String) {

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return super.shouldOverrideUrlLoading(view, url)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                refresh.isRefreshing = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                refresh.isRefreshing = false
            }
        }


        webView.loadUrl(url)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_anim_not_change, R.anim.activity_anim_top_to_bottom)
    }

    companion object {
        fun startActivity(activity: Activity, url: String?, title: String?) {
            val intent = Intent(activity, WebActivity::class.java)
            intent.putExtra("url", url)
            intent.putExtra("title", title)
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.activity_anim_bottom_to_top, R.anim.activity_anim_not_change)
        }
    }

}