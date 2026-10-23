@file:Suppress("PackageName")

package com.example.KSHLatio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.ksh_latio.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * =====================================================================
 * KUIS PERTEMUAN 8 - QUIZ TIF
 * SOAL 2: VALIDASI REGISTRASI & SHAREDPREFERENCES
 * File: RegisterActivity.kt
 * =====================================================================
 */
class RegisterActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_EMAIL = "extra_email"
        const val PREF_NAME = "user_pref"
        const val KEY_NAMA = "registered_nama"
        const val KEY_EMAIL = "registered_email"
        const val KEY_USERNAME = "registered_username"
        const val KEY_PASSWORD = "registered_password"
    }

    private lateinit var etNama: EditText
    private lateinit var etEmail: EditText
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegistrasi: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etNama = findViewById(R.id.etRegisterNama)
        etEmail = findViewById(R.id.etRegisterEmail)
        etUsername = findViewById(R.id.etRegisterUsername)
        etPassword = findViewById(R.id.etRegisterPassword)
        btnRegistrasi = findViewById(R.id.btnProsesRegistrasi)

        // =====================================================================
        // SOAL 2: EMAIL OTOMATIS TERISI DARI INTENT & TIDAK BOLEH DIUBAH
        // =====================================================================
        val emailFromExtra = intent.getStringExtra(EXTRA_EMAIL).orEmpty()
        etEmail.setText(emailFromExtra)
        etEmail.isEnabled = false // Email tidak boleh diedit oleh pengguna
        etEmail.isFocusable = false

        // =====================================================================
        // SOAL 2: ATURAN VALIDASI REGISTRASI
        // =====================================================================
        btnRegistrasi.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val rawUsername = etUsername.text.toString()
            val username = rawUsername.trim()
            val password = etPassword.text.toString()

            // -----------------------------------------------------------------
            // VALIDASI 1: Semua field wajib diisi
            // -----------------------------------------------------------------
            if (nama.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                showErrorDialog("Semua field (Nama, Email, Username, Password) wajib diisi!")
                return@setOnClickListener
            }

            // -----------------------------------------------------------------
            // VALIDASI 2: Username tidak boleh mengandung spasi
            // -----------------------------------------------------------------
            if (rawUsername.contains(" ")) {
                showErrorDialog("Username tidak boleh mengandung karakter spasi!")
                return@setOnClickListener
            }

            // -----------------------------------------------------------------
            // VALIDASI 3: Password minimal 6 karakter
            // -----------------------------------------------------------------
            if (password.length < 6) {
                showErrorDialog("Password minimal harus 6 karakter!")
                return@setOnClickListener
            }

            // =================================================================
            // SOAL 2: SIMPAN SELURUH DATA REGISTRASI KE SHAREDPREFERENCES
            // =================================================================
            val sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(KEY_NAMA, nama)
            editor.putString(KEY_EMAIL, email)
            editor.putString(KEY_USERNAME, username)
            editor.putString(KEY_PASSWORD, password)
            editor.apply()

            // =================================================================
            // SOAL 2: TAMPILKAN HALAMAN INFORMASI "REGISTRASI BERHASIL"
            // =================================================================
            val intentSuccess = Intent(this, RegisterSuccessActivity::class.java).apply {
                putExtra("USER_NAMA", nama)
                putExtra("USER_EMAIL", email)
            }
            startActivity(intentSuccess)
            finish()
        }
    }

    // =====================================================================
    // SOAL 2: MATERIAL ALERT DIALOG UNTUK ERROR VALIDASI REGISTRASI
    // =====================================================================
    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Registrasi Gagal")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}