@file:Suppress("PackageName")

package com.example.KSHLatio.home.pertemuan_3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ksh_latio.databinding.ActivityThirdResultBinding

class ThirdResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("onCreate", "ThirdResultActivity dibuat pertama kali")

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val noTujuan = intent.getStringExtra("noTujuan") ?: ""
        if (noTujuan.isNotBlank()) {
            binding.tvResultText.text = "Kode berhasil dikirim ke $noTujuan! Silahkan periksa nomor Anda."
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: ThirdResultActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "ThirdResultActivity dihapus dari stack")
    }
}