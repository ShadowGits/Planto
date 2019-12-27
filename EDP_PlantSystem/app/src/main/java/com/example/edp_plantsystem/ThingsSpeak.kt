package com.example.edp_plantsystem

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.opengl.ETC1.getWidth
import android.content.Context.WINDOW_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.view.WindowManager
import android.view.Display

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.webkit.WebSettings
import android.util.DisplayMetrics






class ThingsSpeak : AppCompatActivity() {

//    private fun getScale(): Int {
//        val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
//        val width = display.width
//        var `val`: Double? = width / Double(PIC_WIDTH)
//        `val` = `val`!! * 100.0
//        return `val`.toInt()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_things_speak)


        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels



        var webView=findViewById<WebView>(R.id.webView)
        webView.setInitialScale(width)
        webView.webViewClient= WebViewClient()

        webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN;

        webView.settings.javaScriptEnabled=true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        webView.isScrollbarFadingEnabled = false
        webView.setInitialScale(1)


        webView.loadUrl("https://thingspeak.com/channels/907091")

            
        }
    }

