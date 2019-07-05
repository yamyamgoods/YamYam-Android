package org.yamyamgoods.yamyam_android.storeweb

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_store_web.*
import org.yamyamgoods.yamyam_android.R


/**
 * Created By Yun Hyeok
 * on 7월 05, 2019
 */

class StoreWebActivity : AppCompatActivity() {

    private val mWebViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            pb_store_web_act_progressbar.visibility = View.VISIBLE
        }
    }

    private val mWebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            pb_store_web_act_progressbar.progress = newProgress
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_web)

        viewInit()
    }

    override fun onBackPressed() {
        if (wv_store_web_act_web.canGoBack()) {
            wv_store_web_act_web.goBack()
            return
        }
        super.onBackPressed()
    }


    private fun viewInit() {
        wv_store_web_act_web.apply {
            webViewClient = mWebViewClient
            webChromeClient = mWebChromeClient
            applyWebViewSettings()
            loadUrl("https://nightmare73.blog.me")
        }

        btn_store_web_act_close.setOnClickListener {
            finish()
        }

        val color = ContextCompat.getColor(this, R.color.MainYellow)
        pb_store_web_act_progressbar.progressDrawable.setColorFilter(
                color, android.graphics.PorterDuff.Mode.SRC_IN)
    }

    private fun applyWebViewSettings() {
        wv_store_web_act_web.settings.apply {
            javaScriptEnabled = true // 웹페이지 자바스클비트 허용 여부
            loadWithOverviewMode = true // 메타태그 허용 여부
            useWideViewPort = true // 화면 사이즈 맞추기 허용 여부
            setSupportZoom(false) // 화면 줌 허용 여부
            builtInZoomControls = false // 화면 확대 축소 허용 여부
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN // 컨텐츠 사이즈 맞추기
            cacheMode = WebSettings.LOAD_NO_CACHE // 브라우저 캐시 허용 여부
            domStorageEnabled = true // 로컬저장소 허용 여부
        }
    }

}
