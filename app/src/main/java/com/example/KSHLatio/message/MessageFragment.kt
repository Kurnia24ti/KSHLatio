@file:Suppress("PackageName")

package com.example.KSHLatio.message

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ksh_latio.R
import com.example.ksh_latio.databinding.FragmentMessageBinding
import com.example.KSHLatio.message.tutorial.TutorialMessageActivity

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        MessageModel("Mandor Budi", "Pemupukan NPK Lahan Blok A telah selesai 100%.", "https://avatar.iran.liara.run/public/1"),
        MessageModel("Mandor Agus", "Panen Blok C mencapai target 1.477 Kg kelapa sawit.", "https://avatar.iran.liara.run/public/2"),
        MessageModel("Sistem SawitMON", "Pengajuan B.A. 4 telah disetujui oleh Pemilik Kebun.", "https://avatar.iran.liara.run/public/3"),
        MessageModel("Pekerja Dika", "Pembersihan gulma Blok D dijadwalkan besok jam 08.00 WIB.", "https://avatar.iran.liara.run/public/4"),
        MessageModel("Staf Gudang Eka", "Stok pupuk NPK bertambah 50 karung di gudang utama.", "https://avatar.iran.liara.run/public/5"),
        MessageModel("Pekerja Fajar", "Laporan irigasi Blok E siap ditinjau.", "https://avatar.iran.liara.run/public/6"),
        MessageModel("Mandor Gita", "Penyemprotan pestisida Blok B selesai tanpa kendala.", "https://avatar.iran.liara.run/public/7"),
        MessageModel("Sistem SawitMON", "Grafik kinerja kebun bulan ini meningkat +12%.", "https://avatar.iran.liara.run/public/8"),
        MessageModel("Mandor Irfan", "Jadwal pemangkasan pelepah tua Blok A minggu depan.", "https://avatar.iran.liara.run/public/9"),
        MessageModel("Sistem SawitMON", "Selamat datang di fitur Pusat Notifikasi SawitMON!", "https://avatar.iran.liara.run/public/10")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("DEPRECATION")
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Pusat Notifikasi & Pesan"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }

    @Suppress("DEPRECATION")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.message_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Suppress("DEPRECATION")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressedDispatcher.onBackPressed()
                true
            }
            R.id.action_tutorial -> {
                startActivity(Intent(requireContext(), TutorialMessageActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}