package tv.broadpeak.simid.app

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color.TRANSPARENT
import android.provider.Settings
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient


class UpTalkTVController(
    private val context: Context,
    private val apiKey: String,
    private val chatId: String = ""

) {
    private var deviceId: String? = Settings.Secure.getString(
        context.contentResolver,
        Settings.Secure.ANDROID_ID
    )
    private var upTalkTvAppUrl: String = String.format("https://iframe.uptalktv.com?apiKey=%s&chatId=%s&deviceId=%s", apiKey, chatId, deviceId);

    @SuppressLint("SetJavaScriptEnabled")
    fun createWebView(): WebView {
        return WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            setBackgroundColor(TRANSPARENT)

            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                useWideViewPort = true
                loadWithOverviewMode = true
            }

            webViewClient = WebViewClient()

            loadUrl(upTalkTvAppUrl)
        }
    }
}