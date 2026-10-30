@file:Suppress("PackageName")

package com.example.KSHLatio

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ksh_latio.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        Log.e("onCreate", "SplashActivity dibuat pertama kali")

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.splashMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isDarkMode = sharedPref.getBoolean("isDarkMode", false)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Delay 2,5 detik sebelum mengecek SharedPreferences
        Handler(Looper.getMainLooper()).postDelayed({
            val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false) || sharedPref.getBoolean("isLogin", false)

            val intent = if (isLoggedIn) {
                Intent(this, BaseActivity::class.java)
            } else {
                Intent(this, LoginActivity::class.java)
            }

            startActivity(intent)
            finish()
        }, 2500)
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "SplashActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "SplashActivity dihapus dari stack")
    }
}