@file:Suppress("PackageName")

package com.example.KSHLatio

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ksh_latio.R

class LoginActivity : AppCompatActivity() {

    private var passwordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Pilih hak akses (Petani vs Pekerja)
        val btnPetani = findViewById<TextView>(R.id.btnPetani)
        val btnPekerja = findViewById<TextView>(R.id.btnPekerja)

        // Set default selected state
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

        // Show / hide password toggle
        val etPassword = findViewById<EditText>(R.id.etPassword)
        findViewById<ImageView>(R.id.ivToggle)?.setOnClickListener {
            passwordVisible = !passwordVisible
            etPassword?.transformationMethod =
                if (passwordVisible) HideReturnsTransformationMethod.getInstance()
                else PasswordTransformationMethod.getInstance()
            etPassword?.text?.length?.let { len -> etPassword.setSelection(len) }
        }

        // Tombol masuk ke dashboard
        val etEmail = findViewById<EditText>(R.id.etEmail)
        findViewById<Button>(R.id.btnLogin)?.setOnClickListener {
            val email = etEmail?.text?.toString().orEmpty()
            val pass = etPassword?.text?.toString().orEmpty()

            if (email.isBlank() || pass.isBlank()) {
                Toast.makeText(this, "Email dan password wajib diisi", Toast.LENGTH_SHORT).show()
            } else {
                // Simpan status login di SharedPreferences
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                sharedPref.edit().putBoolean("isLoggedIn", true).apply()

                val intent = Intent(this, LandingActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Action daftar, lupa password, & login Google
        findViewById<TextView>(R.id.tvDaftar)?.setOnClickListener {
            Toast.makeText(this, "Halaman pendaftaran", Toast.LENGTH_SHORT).show()
        }
        findViewById<TextView>(R.id.tvLupa)?.setOnClickListener {
            Toast.makeText(this, "Fitur lupa password segera hadir", Toast.LENGTH_SHORT).show()
        }
        findViewById<LinearLayout>(R.id.btnGoogle)?.setOnClickListener {
            Toast.makeText(this, "Login Google segera hadir", Toast.LENGTH_SHORT).show()
        }
    }
}