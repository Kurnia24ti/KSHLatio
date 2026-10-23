@file:Suppress("PackageName")

package com.example.KSHLatio

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.ksh_latio.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * =====================================================================
 * KUIS PERTEMUAN 8 - QUIZ TIF
 * SOAL 1: REGISTRATION WITH GMAIL (LOGIC INPUT EMAIL & VALIDASI DOMAIN)
 * File: GmailInputActivity.kt
 * =====================================================================
 */
class GmailInputActivity : AppCompatActivity() {

    private lateinit var etGmailEmail: EditText
    private lateinit var btnLanjutRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gmail_input)

        etGmailEmail = findViewById(R.id.etGmailEmail)
        btnLanjutRegister = findViewById(R.id.btnLanjutRegister)

        // =====================================================================
        // SOAL 1: VALIDASI EMAIL SAAT TOMBOL LANJUTKAN REGISTRASI DITEKAN
        // =====================================================================
        btnLanjutRegister.setOnClickListener {
            val emailInput = etGmailEmail.text.toString().trim()

            // -----------------------------------------------------------------
            // VALIDASI 1: Email tidak boleh kosong
            // -----------------------------------------------------------------
            if (emailInput.isEmpty()) {
                showMaterialErrorDialog(
                    title = "Validasi Gagal",
                    message = "Email tidak boleh kosong. Silakan masukkan email Gmail Anda."
                )
                return@setOnClickListener
            }

            // -----------------------------------------------------------------
            // VALIDASI 2: Email harus menggunakan domain @gmail.com
            // -----------------------------------------------------------------
            val isFormatValid = Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()
            val isGmailDomain = emailInput.lowercase().endsWith("@gmail.com")
            val usernamePrefix = emailInput.substringBefore("@gmail.com", "")

            if (!isFormatValid || !isGmailDomain || usernamePrefix.isBlank() || emailInput.contains(" ")) {
                showMaterialErrorDialog(
                    title = "Domain Email Tidak Valid",
                    message = "Email harus menggunakan format Gmail yang valid dan berdomain @gmail.com (contoh: nama@gmail.com)."
                )
                return@setOnClickListener
            }

            // -----------------------------------------------------------------
            // SOAL 1: JIKA VALID -> ARAHKAN KE HALAMAN REGISTRASI & BAWA EMAIL
            // -----------------------------------------------------------------
            val intent = Intent(this, RegisterActivity::class.java).apply {
                putExtra(RegisterActivity.EXTRA_EMAIL, emailInput)
            }
            startActivity(intent)
            finish()
        }
    }

    // =====================================================================
    // SOAL 1: ERROR MANAGEMENT MENGGUNAKAN MATERIAL ALERT DIALOG
    // =====================================================================
    private fun showMaterialErrorDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Selesai") { dialog, _ ->
                dialog.dismiss()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}