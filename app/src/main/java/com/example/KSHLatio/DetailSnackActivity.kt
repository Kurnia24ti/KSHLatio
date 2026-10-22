@file:Suppress("PackageName")

package com.example.KSHLatio

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ksh_latio.R
import com.example.ksh_latio.databinding.ActivityDetailSnackBinding

class DetailSnackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSnackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSnackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("onCreate", "DetailSnackActivity dibuat pertama kali")

        setSupportActionBar(binding.toolbarSnack)
        supportActionBar?.apply {
            title = getString(R.string.snack_name)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding.btnBeliLatio.setOnClickListener {
            Toast.makeText(this, "Pesanan Latio Pedas Gurih berhasil dibuat!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "DetailSnackActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "DetailSnackActivity dihapus dari stack")
    }
}