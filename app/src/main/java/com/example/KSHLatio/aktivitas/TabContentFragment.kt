@file:Suppress("PackageName")

package com.example.KSHLatio.aktivitas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ksh_latio.databinding.FragmentTabContentBinding
import com.example.KSHLatio.data.PanenAdapter
import com.example.KSHLatio.data.PanenModel
import com.example.KSHLatio.util.ToastHelper

class TabContentFragment : Fragment() {

    private var _binding: FragmentTabContentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabType = arguments?.getInt(ARG_TAB_TYPE, 0) ?: 0

        val items = when (tabType) {
            0 -> listOf(
                PanenModel("Pengajuan B.A. 4", "Blok C · Panen selesai", "Diajukan", "08.42 WIB", ""),
                PanenModel("Pengajuan B.A. 6", "Blok A · Menunggu verifikasi", "Diajukan", "08.16 WIB", ""),
                PanenModel("Pengajuan B.A. 1", "Blok D · Laporan baru", "Diajukan", "Kemarin", "")
            )
            1 -> listOf(
                PanenModel("Penanganan Hama Blok B", "Penyemprotan pestisida organikal", "Diproses", "10.15 WIB", ""),
                PanenModel("Perbaikan Irigasi Blok E", "Perbaikan saluran air utama", "Diproses", "Kemarin", "")
            )
            else -> listOf(
                PanenModel("Hasil Panen Blok A", "Total 1.477 Kg kelapa sawit", "Selesai", "06.30 WIB", ""),
                PanenModel("Hasil Panen Blok C", "Total 960 Kg kelapa sawit", "Selesai", "2 hari lalu", "")
            )
        }

        binding.rvTabItems.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTabItems.adapter = PanenAdapter(items) { item ->
            ToastHelper.showInfo(requireContext(), "Item dipilih: ${item.title}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TAB_TYPE = "tab_type"
        fun newInstance(tabType: Int): TabContentFragment {
            val fragment = TabContentFragment()
            val args = Bundle()
            args.putInt(ARG_TAB_TYPE, tabType)
            fragment.arguments = args
            return fragment
        }
    }
}