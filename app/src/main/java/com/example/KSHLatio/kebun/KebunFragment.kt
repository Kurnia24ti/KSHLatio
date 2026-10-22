@file:Suppress("PackageName")

package com.example.KSHLatio.kebun

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ksh_latio.R
import com.example.ksh_latio.databinding.FragmentKebunBinding
import com.example.KSHLatio.BaseActivity
import com.example.KSHLatio.DetailSnackActivity
import com.example.KSHLatio.data.DummyData
import com.example.KSHLatio.util.addAktivitas
import com.example.KSHLatio.util.addKebunStat
import com.example.KSHLatio.util.toDisplayName
import com.google.android.material.snackbar.Snackbar

class KebunFragment : Fragment() {

    private var _binding: FragmentKebunBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentKebunBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val act = requireActivity() as AppCompatActivity
        act.setSupportActionBar(binding.toolbar)
        act.supportActionBar?.title = "Data Kebun"

        val pref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val nama = (pref.getString("username", "Anto") ?: "Anto").toDisplayName()
        binding.tvWelcome.text = getString(R.string.kbn_welcome, nama)

        binding.rowKebun.addKebunStat("2,5 Ha", "Luas kebun",
            R.drawable.ic_map, R.color.primary, R.color.mint) {
            openDetail("Luas Kebun", "Lahan terdaftar atas nama $nama", "2,5 Ha")
        }
        binding.rowKebun.addKebunStat("128", "Pohon produktif",
            R.drawable.ic_eco, R.color.amber, R.color.amber_soft) {
            Snackbar.make(binding.root, "128 pohon produktif terpantau", Snackbar.LENGTH_SHORT).show()
        }
        binding.tvLihatDetail.setOnClickListener {
            openDetail("Ringkasan Kebun", "Luas lahan dan pohon produktif", "2,5 Ha · 128 pohon")
        }

        binding.llAktivitas.addAktivitas(
            DummyData.Aktivitas("Data panen ditambahkan", "Hari ini · 06.45 WIB", "", ""),
            showDivider = false,
            iconRes = R.drawable.ic_eco
        ) {
            Snackbar.make(binding.root, "Data panen ditambahkan hari ini", Snackbar.LENGTH_SHORT).show()
        }
        binding.tvLihatSemua.setOnClickListener {
            (requireActivity() as? BaseActivity)?.selectTab(R.id.aktivitas)
        }
    }

    private fun openDetail(title: String, subtitle: String, value: String) {
        val intent = Intent(requireContext(), DetailSnackActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("subtitle", subtitle)
        intent.putExtra("value", value)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}