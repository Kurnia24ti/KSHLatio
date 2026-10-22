@file:Suppress("PackageName")

package com.example.KSHLatio.aktivitas

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.ksh_latio.R
import com.example.ksh_latio.databinding.FragmentAktivitasBinding
import com.example.KSHLatio.BaseActivity
import com.example.KSHLatio.data.DummyData
import com.example.KSHLatio.util.addStatusStat
import com.example.KSHLatio.util.toDisplayName
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class AktivitasFragment : Fragment() {

    private var _binding: FragmentAktivitasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAktivitasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val act = requireActivity() as AppCompatActivity
        act.setSupportActionBar(binding.toolbar)
        act.supportActionBar?.setDisplayShowTitleEnabled(false)

        val pref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val nama = (pref.getString("username", "Anto") ?: "Anto").toDisplayName()
        binding.tvAvatar.text = nama.take(2).uppercase()

        binding.tvAvatar.setOnClickListener { (requireActivity() as? BaseActivity)?.openDrawer() }

        binding.flBell.setOnClickListener {
            binding.viewNotifDot.visibility = View.INVISIBLE
            Snackbar.make(binding.root, "Semua notifikasi sudah dibaca", Snackbar.LENGTH_SHORT)
                .setAction("Tutup") { Log.e("Info Snackbar", "Snackbar ditutup") }
                .show()
        }

        binding.btnBuatAksi.setOnClickListener { showCreateActionDialog() }

        binding.tvHariIni.setOnClickListener {
            Snackbar.make(binding.root, "Menampilkan ringkasan hari ini", Snackbar.LENGTH_SHORT).show()
        }

        binding.rowStat1.addStatusStat("Pengajuan", "0", "baru",
            R.drawable.ic_description, R.color.primary, R.color.mint) { toastInfo("Pengajuan baru: 0") }
        binding.rowStat1.addStatusStat("Penanganan", "0", "aktif",
            R.drawable.ic_build, R.color.blue, R.color.blue_soft) { toastInfo("Penanganan aktif: 0") }
        binding.rowStat2.addStatusStat("Total panen", "0 Kg", "bulan ini",
            R.drawable.ic_inventory, R.color.amber, R.color.amber_soft) { toastInfo("Total panen bulan ini: 0 Kg") }
        binding.rowStat2.addStatusStat("Aktivitas hari ini", "0", "kegiatan",
            R.drawable.ic_show_chart, R.color.purple, R.color.purple_soft) { toastInfo("Aktivitas hari ini: 0 kegiatan") }

        binding.tvBulan.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Pilih bulan")
                .setItems(DummyData.bulan) { _, which ->
                    binding.tvBulan.text = "${DummyData.bulan[which]} ▾"
                    Snackbar.make(binding.root, "Menampilkan ${DummyData.bulan[which]}", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }

        val tabs = listOf(binding.tabPengajuan, binding.tabPenanganan, binding.tabPanen)
        fun selectTab(active: TextView) {
            tabs.forEach { tab ->
                val on = tab == active
                tab.setBackgroundResource(if (on) R.drawable.bg_tab_active else 0)
                tab.setTextColor(ContextCompat.getColor(requireContext(), if (on) R.color.white else R.color.text_gray))
                tab.paint.isFakeBoldText = on
                tab.invalidate()
            }
            binding.tvEmptyTab.text = when (active.id) {
                R.id.tabPengajuan -> "Belum ada aktivitas terbaru"
                R.id.tabPenanganan -> "Belum ada penanganan terbaru"
                else -> "Belum ada data panen terbaru"
            }
        }
        tabs.forEach { tab -> tab.setOnClickListener { selectTab(tab) } }
        selectTab(binding.tabPengajuan)

        binding.tvLihatSemuaAkt.setOnClickListener {
            Snackbar.make(binding.root, "Belum ada aktivitas untuk ditampilkan", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun showCreateActionDialog() {
        val pilihan = arrayOf("Pengajuan baru", "Penanganan", "Catat hasil panen")
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Buat aksi")
            .setItems(pilihan) { _, which ->
                Snackbar.make(binding.root, "Aksi dipilih: ${pilihan[which]}", Snackbar.LENGTH_SHORT).show()
            }
            .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
            .show()
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