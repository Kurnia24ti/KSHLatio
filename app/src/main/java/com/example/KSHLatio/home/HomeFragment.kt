@file:Suppress("PackageName")

package com.example.KSHLatio.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ksh_latio.R
import com.example.ksh_latio.databinding.FragmentHomeBinding
import com.example.KSHLatio.BaseActivity
import com.example.KSHLatio.DetailSnackActivity
import com.example.KSHLatio.data.DummyData
import com.example.KSHLatio.util.addAktivitas
import com.example.KSHLatio.util.addBigStat
import com.example.KSHLatio.util.addOpsStat
import com.example.KSHLatio.util.toDisplayName
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val act = requireActivity() as AppCompatActivity
        act.setSupportActionBar(binding.toolbar)
        act.supportActionBar?.setDisplayShowTitleEnabled(false)

        val pref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val nama = (pref.getString("username", "Anto") ?: "Anto").toDisplayName()

        binding.tvAvatar.text = nama.take(2).uppercase()
        binding.tvTanggal.text = SimpleDateFormat("EEE, dd MMM yyyy", Locale("id", "ID")).format(Date())

        binding.tvAvatar.setOnClickListener { (requireActivity() as? BaseActivity)?.openDrawer() }
        binding.ivBell.setOnClickListener {
            Snackbar.make(binding.root, "Tidak ada notifikasi baru", Snackbar.LENGTH_SHORT)
                .setAction("Tutup") { Log.e("Info Snackbar", "Snackbar ditutup") }
                .show()
        }

        binding.rowTop.addBigStat("Total Panen", "1477 Kg", "Panen bulan ini", badge = "+12%", filled = true) {
            openDetail("Total Panen", "Panen bulan ini", "1477 Kg")
        }
        binding.rowTop.addBigStat("Produktivitas", "3", "Ton / hektar", showTrend = true) {
            openDetail("Produktivitas", "Rata-rata ton per hektar", "3 Ton/Ha")
        }

        binding.rowOps1.addOpsStat("Aktivitas", "Belum ada kegiatan", "0",
            R.drawable.ic_show_chart, R.color.blue, R.color.blue_soft) { toastInfo("Aktivitas: belum ada kegiatan") }
        binding.rowOps1.addOpsStat("Pengajuan", "Perlu ditinjau", "3",
            R.drawable.ic_assignment, R.color.amber, R.color.amber_soft) { toastInfo("Pengajuan: 3 perlu ditinjau") }
        binding.rowOps2.addOpsStat("Pegawai", "Aktif hari ini", "10",
            R.drawable.ic_people, R.color.primary, R.color.mint) { toastInfo("Pegawai: 10 aktif hari ini") }
        binding.rowOps2.addOpsStat("Lahan aktif", "Blok terpantau", "8",
            R.drawable.ic_map, R.color.primary, R.color.mint) { toastInfo("Lahan aktif: 8 blok terpantau") }

        binding.tvLihatDetail.setOnClickListener {
            openDetail("Operasional Hari Ini", "Aktivitas, pengajuan, pegawai dan lahan", "4 kategori")
        }

        binding.chartPanen.setData(DummyData.panenBulanan)
        binding.tvPeriode.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Pilih periode")
                .setItems(DummyData.periode) { _, which ->
                    val n = when (which) { 0 -> 3; 1 -> 6; else -> DummyData.panenBulanan.size }
                    binding.chartPanen.setData(DummyData.panenBulanan.takeLast(n))
                    binding.tvPeriode.text = "${DummyData.periode[which]} ▾"
                    binding.tvChartSub.text = "Tren panen selama ${DummyData.periode[which]} terakhir"
                }
                .show()
        }

        binding.tvJumlahAktivitas.text = DummyData.aktivitasTerbaru.size.toString()
        DummyData.aktivitasTerbaru.forEachIndexed { index, item ->
            binding.llAktivitas.addAktivitas(
                item,
                showDivider = index != DummyData.aktivitasTerbaru.lastIndex
            ) {
                openDetail(item.judul, item.detail, item.status)
            }
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

    private fun toastInfo(message: String) {
        Log.e("Klik kartu", message)
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}