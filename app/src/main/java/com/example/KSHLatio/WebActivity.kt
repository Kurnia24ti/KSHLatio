@file:Suppress("PackageName")

package com.example.KSHLatio

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.ksh_latio.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val targetUrl = intent.getStringExtra("URL") ?: "https://merdeka.com"
        val targetTitle = intent.getStringExtra("TITLE") ?: "Web Merdeka"

        // Mengaktifkan Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = targetTitle
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Konfigurasi WebView
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        binding.webView.loadUrl(targetUrl)

        // Agar Toolbar hide/show saat scroll web (CoordinatorLayout & AppBarLayout)
        binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.appBar.setExpanded(false, true)
            } else if (scrollY < oldScrollY) {
                binding.appBar.setExpanded(true, true)
            }
        }

        // Handling tombol back fisik dengan OnBackPressedDispatcher
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (binding.webView.canGoBack()) {
            binding.webView.goBack()
            true
        } else {
            finish()
            true
        }
    }
}