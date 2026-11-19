@file:Suppress("PackageName")

package com.example.KSHLatio.message

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ksh_latio.databinding.ActivityNotifikasiBinding

class NotifikasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotifikasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotifikasiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("onCreate", "NotifikasiActivity dibuat pertama kali")

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.containerNotifikasi.id, MessageFragment())
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: NotifikasiActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "NotifikasiActivity dihapus dari stack")
    }
}