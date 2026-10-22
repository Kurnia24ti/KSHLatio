@file:Suppress("PackageName")

package com.example.KSHLatio

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.Fragment
import com.example.ksh_latio.R
import com.example.ksh_latio.databinding.ActivityBaseBinding
import com.example.KSHLatio.aktivitas.AktivitasFragment
import com.example.KSHLatio.akun.AkunFragment
import com.example.KSHLatio.home.HomeFragment
import com.example.KSHLatio.kebun.KebunFragment
import com.example.KSHLatio.util.applySystemBarsPadding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.main.applySystemBarsPadding()
        Log.e("onCreate", "BaseActivity dibuat pertama kali")

        if (savedInstanceState == null) replaceFragment(HomeFragment())
        highlightDrawer(R.id.home)

        binding.bottomNavView.setOnItemSelectedListener {
            highlightDrawer(it.itemId)
            when (it.itemId) {
                R.id.home -> { replaceFragment(HomeFragment()); true }
                R.id.kebun -> { replaceFragment(KebunFragment()); true }
                R.id.aktivitas -> { replaceFragment(AktivitasFragment()); true }
                R.id.akun -> { replaceFragment(AkunFragment()); true }
                else -> false
            }
        }

        binding.tvMenuDashboard.setOnClickListener { selectTab(R.id.home) }
        binding.tvMenuKebun.setOnClickListener { selectTab(R.id.kebun) }
        binding.tvMenuRiwayat.setOnClickListener { selectTab(R.id.aktivitas) }
        binding.tvMenuProfil.setOnClickListener { selectTab(R.id.akun) }
        binding.ivCloseDrawer.setOnClickListener { binding.drawerLayout.closeDrawer(GravityCompat.START) }
        binding.btnLogoutDrawer.setOnClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            confirmLogout()
        }

        onBackPressedDispatcher.addCallback(this) {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                isEnabled = false
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    fun openDrawer() = binding.drawerLayout.openDrawer(GravityCompat.START)

    fun selectTab(itemId: Int) {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        binding.bottomNavView.selectedItemId = itemId
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

    private fun highlightDrawer(itemId: Int) {
        val menus = mapOf(
            R.id.home to binding.tvMenuDashboard,
            R.id.kebun to binding.tvMenuKebun,
            R.id.aktivitas to binding.tvMenuRiwayat,
            R.id.akun to binding.tvMenuProfil
        )
        menus.forEach { (id, tv) ->
            val active = id == itemId
            tv.setBackgroundResource(if (active) R.drawable.bg_menu_active else 0)
            tv.setTextColor(ContextCompat.getColor(this, if (active) R.color.primary else R.color.text_dark))
            TextViewCompat.setCompoundDrawableTintList(
                tv,
                ColorStateList.valueOf(ContextCompat.getColor(this, if (active) R.color.primary else R.color.text_gray))
            )
            tv.paint.isFakeBoldText = active
        }
    }

    fun confirmLogout() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Konfirmasi")
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { dialog, _ ->
                val editor = getSharedPreferences("user_pref", MODE_PRIVATE).edit()
                editor.clear()
                editor.apply()
                dialog.dismiss()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    override fun onStart() { super.onStart(); Log.e("onStart", "BaseActivity terlihat di layar") }
    override fun onDestroy() { super.onDestroy(); Log.e("onDestroy", "BaseActivity dihapus dari stack") }
}