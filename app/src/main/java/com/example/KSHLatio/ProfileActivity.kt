@file:Suppress("PackageName")

package com.example.KSHLatio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ksh_latio.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("onCreate", "ProfileActivity dibuat pertama kali")

        // Toolbar navigation back
        binding.toolbarProfile.setNavigationOnClickListener {
            finish()
        }

        // Action GitHub button -> Buka profil GitHub di browser
        binding.btnGithub.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Kurnia24ti"))
                startActivity(intent)
            } catch (_: Exception) {
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "ProfileActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "ProfileActivity dihapus dari stack")
    }
}