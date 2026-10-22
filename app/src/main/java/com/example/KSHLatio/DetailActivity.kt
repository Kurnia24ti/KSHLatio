@file:Suppress("PackageName")

package com.example.KSHLatio

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ksh_latio.R
import com.example.ksh_latio.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("onCreate", "DetailActivity dibuat pertama kali")

        val judul = intent.getStringExtra("title") ?: "Detail"
        val subJudul = intent.getStringExtra("subtitle") ?: "-"
        val nilai = intent.getStringExtra("value") ?: "-"
        Log.e("Data Intent", "title=$judul subtitle=$subJudul value=$nilai")

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = judul
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        binding.tvValue.text = nilai
        binding.tvSubtitle.text = subJudul
        binding.tvIsi.text = (1..12).joinToString("\n\n") {
            "Rincian $it: data dummy untuk $judul. Konten panjang ini dibuat agar halaman bisa di-scroll dan Toolbar menyembunyikan diri saat digulir."
        }
        binding.btnKembali.setOnClickListener { finish() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> { onBackPressedDispatcher.onBackPressed(); true }
            R.id.action_search -> { Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show(); true }
            R.id.action_settings -> { Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() { super.onStart(); Log.e("onStart", "DetailActivity terlihat di layar") }
    override fun onDestroy() { super.onDestroy(); Log.e("onDestroy", "DetailActivity dihapus dari stack") }
}