@file:Suppress("PackageName")

package com.example.KSHLatio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ksh_latio.R

class RegisterSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_success)

        val nama = intent.getStringExtra("USER_NAMA").orEmpty()
        val email = intent.getStringExtra("USER_EMAIL").orEmpty()

        val tvInfo = findViewById<TextView>(R.id.tvInfoRegistrasi)
        val btnKeLogin = findViewById<Button>(R.id.btnKeHalamanLogin)

        tvInfo.text = "Selamat $nama!\nAkun Anda ($email) telah berhasil didaftarkan dan tersimpan."

        btnKeLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}