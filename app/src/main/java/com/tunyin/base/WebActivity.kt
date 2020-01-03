package com.tunyin.base

import android.content.Context
import android.content.Intent
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.tunyin.R
import com.tunyin.utils.AppUtils
import com.tunyin.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlin.properties.Delegates


class WebActivity : BaseActivity() {
    override fun getLayoutId()= R.layout.activity_web

    private var url: String by Delegates.notNull()

    override fun initWidget() {
        super.initWidget()
        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.white))
        url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")
        if (title.isNotEmpty()) {
            tv_title.text = title

        }
        val webSetting = webView.settings
        webSetting.cacheMode = WebSettings.LOAD_NO_CACHE
        webSetting.javaScriptEnabled = true
        webSetting.javaScriptCanOpenWindowsAutomatically = true
        webSetting.allowFileAccess = true
        webSetting.domStorageEnabled = true
        webSetting.databaseEnabled = true
        webSetting.builtInZoomControls = true
        webSetting.setSupportZoom(true)
        webSetting.useWideViewPort = true
        webSetting.loadWithOverviewMode = true
        webView.loadUrl(url)
        webView.webChromeClient = object : WebChromeClient() {

        }
        webView.webViewClient = object : WebViewClient() {

        }
        //重要,用于与页面交互!
        webView.addJavascriptInterface(object : Any() {
            @JavascriptInterface
            fun something() {
                webView.post { }
            }
        }, "android")
    }


    companion object {

        @JvmStatic
        fun newIntent(context: Context, title: String, url: String): Intent {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("url", url)
            intent.putExtra("title", title)
            return intent
        }
    }
}