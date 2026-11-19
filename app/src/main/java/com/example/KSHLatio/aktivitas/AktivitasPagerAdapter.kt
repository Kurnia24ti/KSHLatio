@file:Suppress("PackageName")

package com.example.KSHLatio.aktivitas

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class AktivitasPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return TabContentFragment.newInstance(position)
    }
}