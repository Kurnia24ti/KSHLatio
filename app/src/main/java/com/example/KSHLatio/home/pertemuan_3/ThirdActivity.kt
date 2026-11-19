@file:Suppress("PackageName")

package com.example.KSHLatio.home.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ksh_latio.databinding.ActivityThirdBinding
import com.example.KSHLatio.util.ToastHelper

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("onCreate", "ThirdActivity dibuat pertama kali")

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding.btnKirim.setOnClickListener {
            val noTujuan = binding.inputNoTujuan.text.toString().trim()
            if (noTujuan.isEmpty()) {
                ToastHelper.showError(this, "Nomor tujuan tidak boleh kosong!")
            } else {
                Toast.makeText(this, "Mengirim kode ke: $noTujuan", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ThirdResultActivity::class.java)
                intent.putExtra("noTujuan", noTujuan)
                startActivity(intent)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: ThirdActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "ThirdActivity dihapus dari stack")
    }
}