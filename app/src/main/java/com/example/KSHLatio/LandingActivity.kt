@file:Suppress("PackageName")

package com.example.KSHLatio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ksh_latio.R

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_landing)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tombol Mulai Sekarang -> LoginActivity
        findViewById<Button>(R.id.btnMulaiSekarang)?.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Klik subjudul -> Membuka WebView
        findViewById<TextView>(R.id.tvSubjudul)?.setOnClickListener {
            val intent = Intent(this, WebActivity::class.java).apply {
                putExtra("URL", "https://merdeka.com")
                putExtra("TITLE", "Web Merdeka")
            }
            startActivity(intent)
        }

        // Tombol Profil -> Membuka ProfileActivity
        findViewById<TextView>(R.id.btnMenuProfil)?.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Tombol Logout
        findViewById<TextView>(R.id.btnLogout)?.setOnClickListener {
            showLogoutConfirmationDialog()
        }
    }

    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Keluar")
            .setMessage("Apakah Anda yakin ingin keluar dari akun?")
            .setPositiveButton("Ya") { _, _ ->
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                sharedPref.edit().putBoolean("isLoggedIn", false).apply()

                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}