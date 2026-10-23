@file:Suppress("PackageName")

package com.example.KSHLatio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ksh_latio.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * =====================================================================
 * KUIS PERTEMUAN 8 - QUIZ TIF
 * File: LoginActivity.kt
 * =====================================================================
 */
class LoginActivity : AppCompatActivity() {

    private var passwordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.e("onCreate", "LoginActivity dibuat pertama kali")

        // Pilih hak akses (Petani vs Pekerja)
        val btnPetani = findViewById<TextView>(R.id.btnPetani)
        val btnPekerja = findViewById<TextView>(R.id.btnPekerja)

        btnPetani?.isSelected = true
        btnPekerja?.isSelected = false

        btnPetani?.setOnClickListener {
            btnPetani.isSelected = true
            btnPekerja?.isSelected = false
        }
        btnPekerja?.setOnClickListener {
            btnPekerja.isSelected = true
            btnPetani?.isSelected = false
        }

        // Toggle visibility password
        val etPassword = findViewById<EditText>(R.id.etPassword)
        findViewById<ImageView>(R.id.ivToggle)?.setOnClickListener {
            passwordVisible = !passwordVisible
            etPassword?.transformationMethod =
                if (passwordVisible) HideReturnsTransformationMethod.getInstance()
                else PasswordTransformationMethod.getInstance()
            etPassword?.text?.length?.let { len -> etPassword.setSelection(len) }
        }

        // =====================================================================
        // SOAL 1: NAVIGASI REGISTER WITH GMAIL
        // Saat tombol "Register With Gmail" atau link "Daftar sekarang" ditekan,
        // arahkan ke Halaman Input Email (GmailInputActivity)
        // =====================================================================
        val btnRegisterGmail = findViewById<Button>(R.id.btnRegisterGmail)
        btnRegisterGmail?.setOnClickListener {
            val intent = Intent(this, GmailInputActivity::class.java)
            startActivity(intent)
        }

        findViewById<TextView>(R.id.tvDaftar)?.setOnClickListener {
            val intent = Intent(this, GmailInputActivity::class.java)
            startActivity(intent)
        }

        // =====================================================================
        // SOAL 2: LOGIKA LOGIN MULTI-KONDISI (OR / ||) & SHAREDPREFERENCES
        // =====================================================================
        val etEmail = findViewById<EditText>(R.id.etEmail)
        findViewById<Button>(R.id.btnLogin)?.setOnClickListener {
            val inputUser = etEmail?.text?.toString().orEmpty().trim()
            val inputPass = etPassword?.text?.toString().orEmpty()

            if (inputUser.isEmpty() || inputPass.isEmpty()) {
                showMaterialErrorDialog("Login Gagal", "Username/Email dan Password tidak boleh kosong.")
                return@setOnClickListener
            }

            // Read data dari SharedPreferences
            val sharedPref = getSharedPreferences(RegisterActivity.PREF_NAME, Context.MODE_PRIVATE)
            val savedUsername = sharedPref.getString(RegisterActivity.KEY_USERNAME, null)
            val savedPassword = sharedPref.getString(RegisterActivity.KEY_PASSWORD, null)
            val savedEmail = sharedPref.getString(RegisterActivity.KEY_EMAIL, null)

            // -----------------------------------------------------------------
            // KONDISI 1: username == password
            // -----------------------------------------------------------------
            val isConditionOneValid = (inputUser == inputPass)

            // -----------------------------------------------------------------
            // KONDISI 2: username dan password sesuai data di SharedPreferences
            // -----------------------------------------------------------------
            val isConditionTwoValid = (savedPassword != null && inputPass == savedPassword &&
                    (inputUser == savedUsername || inputUser == savedEmail))

            // -----------------------------------------------------------------
            // EVALUASI LOGIKA LOGIN SAMA DENGAN OR (||)
            // loginBerhasil = (username == password) OR (username == savedUser AND password == savedPass)
            // -----------------------------------------------------------------
            if (isConditionOneValid || isConditionTwoValid) {
                // Login Berhasil -> Simpan session
                val editor = sharedPref.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.putBoolean("isLogin", true)
                editor.putString("username", inputUser)
                editor.apply()

                Log.d("Login", "Berhasil login: $inputUser (Cond1: $isConditionOneValid, Cond2: $isConditionTwoValid)")

                // Arahkan ke Halaman Home (BaseActivity)
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Login Gagal -> Tampilkan pesan error dengan MaterialAlertDialog
                showMaterialErrorDialog(
                    title = "Login Gagal",
                    message = "Username/Email atau Password tidak sesuai. Silakan periksa kembali data Anda."
                )
            }
        }

        findViewById<TextView>(R.id.tvLupa)?.setOnClickListener {
            Toast.makeText(this, "Fitur lupa password segera hadir", Toast.LENGTH_SHORT).show()
        }
    }

    // =====================================================================
    // SOAL 2: MATERIAL ALERT DIALOG UNTUK ERROR LOGIN GAGAL
    // =====================================================================
    private fun showMaterialErrorDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Coba Lagi") { dialog, _ ->
                dialog.dismiss()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "LoginActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "LoginActivity dihapus dari stack")
    }
}