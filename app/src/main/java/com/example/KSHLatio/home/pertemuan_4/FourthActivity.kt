@file:Suppress("PackageName")

package com.example.KSHLatio.home.pertemuan_4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ksh_latio.databinding.ActivityFourthBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class FourthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("onCreate", "FourthActivity dibuat pertama kali")

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val name = intent.getStringExtra("name") ?: "Politeknik Caltex Riau"
        val from = intent.getStringExtra("from") ?: "Rumbai"
        val age = intent.getIntExtra("age", 25)

        Log.e("Data Intent", "Nama: $name , Usia: $age, Asal: $from")
        binding.tvIntentData.text = "Data Intent P4:\n• Nama: $name\n• Usia: $age tahun\n• Asal: $from"

        binding.btnShowSnackbar.setOnClickListener {
            Snackbar.make(binding.root, "Ini adalah Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("Tutup") { Log.e("Info Snackbar", "Snackbar ditutup") }
                .show()
        }

        binding.btnShowAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Ya") { dialog, _ ->
                    Log.e("AlertDialog", "Pengguna memilih Ya")
                    dialog.dismiss()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    Log.e("AlertDialog", "Pengguna memilih Batal")
                    dialog.dismiss()
                }
                .show()
        }

        binding.btn1.setOnClickListener {
            Log.e("MaterialCardView", "Tombol btn1 di dalam MaterialCardView diklik")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: FourthActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "FourthActivity dihapus dari stack")
    }
}