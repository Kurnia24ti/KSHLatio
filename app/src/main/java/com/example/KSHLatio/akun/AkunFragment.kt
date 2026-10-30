@file:Suppress("PackageName")

package com.example.KSHLatio.akun

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.ksh_latio.databinding.FragmentAkunBinding
import com.example.KSHLatio.BaseActivity
import com.example.KSHLatio.DetailSnackActivity
import com.example.KSHLatio.WebActivity
import com.example.KSHLatio.util.ToastHelper
import com.example.KSHLatio.util.toDisplayName

class AkunFragment : Fragment() {

    private var _binding: FragmentAkunBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAkunBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val act = requireActivity() as AppCompatActivity
        act.setSupportActionBar(binding.toolbar)
        act.supportActionBar?.title = "Akun"

        val pref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val email = pref.getString("username", "anto") ?: "anto"
        val nama = email.toDisplayName()
        binding.tvAvatar.text = nama.take(2).uppercase()
        binding.tvNama.text = nama
        binding.tvEmail.text = email
        binding.tvRole.text = pref.getString("role", "Petani")

        // FITUR 3: Dark Mode / Light Mode Toggle
        val isDarkMode = pref.getBoolean("isDarkMode", false)
        binding.switchDarkMode.isChecked = isDarkMode

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            pref.edit().putBoolean("isDarkMode", isChecked).apply()

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                ToastHelper.showInfo(requireContext(), "Mode Gelap Diaktifkan 🌙")
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                ToastHelper.showInfo(requireContext(), "Mode Terang Diaktifkan ☀️")
            }
        }

        binding.btnBantuan.setOnClickListener {
            startActivity(Intent(requireContext(), WebActivity::class.java))
        }
        binding.btnTentang.setOnClickListener {
            val intent = Intent(requireContext(), DetailSnackActivity::class.java)
            intent.putExtra("title", "Tentang SawitMon")
            intent.putExtra("subtitle", "Platform Monitoring Perkebunan Kelapa Sawit Digital")
            intent.putExtra("value", "v1.0")
            startActivity(intent)
        }
        binding.btnLogout.setOnClickListener { (requireActivity() as? BaseActivity)?.confirmLogout() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}