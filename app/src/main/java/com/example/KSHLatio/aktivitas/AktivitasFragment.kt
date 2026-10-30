@file:Suppress("PackageName")

package com.example.KSHLatio.aktivitas

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.ksh_latio.R
import com.example.ksh_latio.databinding.FragmentAktivitasBinding
import com.example.KSHLatio.BaseActivity
import com.example.KSHLatio.data.DummyData
import com.example.KSHLatio.util.ToastHelper
import com.example.KSHLatio.util.addStatusStat
import com.example.KSHLatio.util.toDisplayName
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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
            ToastHelper.showInfo(requireContext(), "Semua notifikasi sudah dibaca")
        }

        binding.btnBuatAksi.setOnClickListener { showCreateActionDialog() }

        binding.tvHariIni.setOnClickListener {
            ToastHelper.showInfo(requireContext(), "Menampilkan ringkasan hari ini")
        }

        // FITUR 1: Interactive Calendar Listener
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val formattedDate = "$dayOfMonth ${DummyData.bulan.getOrElse(month) { "Bulan" }} $year"
            binding.tvCalendarSelectedDate.text = "$dayOfMonth/${month + 1}/$year"

            val sampleAgenda = when (dayOfMonth % 3) {
                0 -> "• 08.00 WIB: Pemupukan NPK Lahan Blok A\n• 14.00 WIB: Inspeksi Kualitas Panen Kelapa Sawit"
                1 -> "• 07.30 WIB: Panen Kelapa Sawit Blok C (Target 1,5 Ton)\n• 13.00 WIB: Timbang Hasil Panen"
                else -> "• 09.00 WIB: Pembersihan Gulma & Perawatan Pohon Blok D\n• 15.30 WIB: Evaluasi Kinerja Pegawai"
            }
            binding.tvAgendaTitle.text = "🌱 Agenda Perkebunan ($formattedDate):"
            binding.tvAgendaContent.text = sampleAgenda

            // FITUR 2: Trigger Toast Notification
            ToastHelper.showSuccess(requireContext(), "Jadwal Perkebunan Tanggal $dayOfMonth dipilih")
        }

        binding.rowStat1.addStatusStat("Pengajuan", "0", "baru",
            R.drawable.ic_description, R.color.primary, R.color.mint) {
            ToastHelper.showInfo(requireContext(), "Pengajuan baru: 0")
        }
        binding.rowStat1.addStatusStat("Penanganan", "0", "aktif",
            R.drawable.ic_build, R.color.blue, R.color.blue_soft) {
            ToastHelper.showInfo(requireContext(), "Penanganan aktif: 0")
        }
        binding.rowStat2.addStatusStat("Total panen", "0 Kg", "bulan ini",
            R.drawable.ic_inventory, R.color.amber, R.color.amber_soft) {
            ToastHelper.showInfo(requireContext(), "Total panen bulan ini: 0 Kg")
        }
        binding.rowStat2.addStatusStat("Aktivitas hari ini", "0", "kegiatan",
            R.drawable.ic_show_chart, R.color.purple, R.color.purple_soft) {
            ToastHelper.showInfo(requireContext(), "Aktivitas hari ini: 0 kegiatan")
        }

        binding.tvBulan.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Pilih bulan")
                .setItems(DummyData.bulan) { _, which ->
                    binding.tvBulan.text = "${DummyData.bulan[which]} ▾"
                    ToastHelper.showInfo(requireContext(), "Menampilkan ${DummyData.bulan[which]}")
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
            ToastHelper.showInfo(requireContext(), "Belum ada aktivitas untuk ditampilkan")
        }
    }

    private fun showCreateActionDialog() {
        val pilihan = arrayOf("Pengajuan baru", "Penanganan", "Catat hasil panen")
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Buat aksi")
            .setItems(pilihan) { _, which ->
                ToastHelper.showSuccess(requireContext(), "Aksi berhasil dibuat: ${pilihan[which]}")
            }
            .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}