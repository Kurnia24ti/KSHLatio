@file:Suppress("PackageName")

package com.example.KSHLatio.message.tutorial

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ksh_latio.R

class TutorialFragmentAdapter(
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {

    private val fragments = listOf(
        TutorialItemFragment.newInstance(
            "Pusat Notifikasi SawitMON",
            "Pantau seluruh pengajuan, penanganan lahan, dan update panen secara langsung.",
            R.drawable.ic_notifications
        ),
        TutorialItemFragment.newInstance(
            "Informasi Lahan & Pekerja",
            "Dapatkan laporan pemupukan, irigasi, dan pembersihan gulma dari mandor kebun.",
            R.drawable.ic_map
        ),
        TutorialItemFragment.newInstance(
            "Kemudahan Akses 24/7",
            "Semua riwayat dan data operasional kebun tersimpan aman di genggaman Anda.",
            R.drawable.ic_eco
        )
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}