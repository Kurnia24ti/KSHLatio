@file:Suppress("PackageName")

package com.example.KSHLatio.util

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.ksh_latio.R
import com.example.ksh_latio.databinding.ItemAktivitasBinding
import com.example.ksh_latio.databinding.ItemStatBigBinding
import com.example.ksh_latio.databinding.ItemStatKebunBinding
import com.example.ksh_latio.databinding.ItemStatOpsBinding
import com.example.ksh_latio.databinding.ItemStatStatusBinding
import com.example.KSHLatio.data.DummyData

private fun ImageView.tintIcon(iconRes: Int, iconColor: Int, softColor: Int) {
    setImageResource(iconRes)
    imageTintList = ColorStateList.valueOf(ContextCompat.getColor(context, iconColor))
    backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, softColor))
}

fun LinearLayout.addBigStat(
    label: String, value: String, sub: String,
    badge: String? = null, filled: Boolean = false, showTrend: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val b = ItemStatBigBinding.inflate(LayoutInflater.from(context), this, false)
    b.tvLabel.text = label
    b.tvValue.text = value
    b.tvSub.text = sub
    if (filled) {
        b.root.setCardBackgroundColor(ContextCompat.getColor(context, R.color.mint))
        b.root.strokeColor = ContextCompat.getColor(context, R.color.mint_stroke)
    }
    if (badge != null) {
        b.tvBadge.text = badge
        b.tvBadge.visibility = View.VISIBLE
    }
    if (showTrend) b.ivTrend.visibility = View.VISIBLE
    b.root.setOnClickListener { onClick?.invoke() }
    addView(b.root)
}

fun LinearLayout.addOpsStat(
    label: String, sub: String, value: String,
    iconRes: Int, iconColor: Int, softColor: Int,
    onClick: (() -> Unit)? = null
) {
    val b = ItemStatOpsBinding.inflate(LayoutInflater.from(context), this, false)
    b.tvLabel.text = label
    b.tvSub.text = sub
    b.tvValue.text = value
    b.ivIcon.tintIcon(iconRes, iconColor, softColor)
    b.root.setOnClickListener { onClick?.invoke() }
    addView(b.root)
}

fun LinearLayout.addStatusStat(
    label: String, value: String, unit: String,
    iconRes: Int, iconColor: Int, softColor: Int,
    onClick: (() -> Unit)? = null
) {
    val b = ItemStatStatusBinding.inflate(LayoutInflater.from(context), this, false)
    b.tvLabel.text = label
    b.tvValue.text = value
    b.tvUnit.text = unit
    b.ivIcon.tintIcon(iconRes, iconColor, softColor)
    b.root.setOnClickListener { onClick?.invoke() }
    addView(b.root)
}

fun LinearLayout.addKebunStat(
    value: String, label: String,
    iconRes: Int, iconColor: Int, softColor: Int,
    onClick: (() -> Unit)? = null
) {
    val b = ItemStatKebunBinding.inflate(LayoutInflater.from(context), this, false)
    b.tvValue.text = value
    b.tvLabel.text = label
    b.ivIcon.tintIcon(iconRes, iconColor, softColor)
    b.root.setOnClickListener { onClick?.invoke() }
    addView(b.root)
}

fun LinearLayout.addAktivitas(
    item: DummyData.Aktivitas,
    showDivider: Boolean = true,
    iconRes: Int = R.drawable.ic_description,
    onClick: (() -> Unit)? = null
) {
    val b = ItemAktivitasBinding.inflate(LayoutInflater.from(context), this, false)
    b.tvJudul.text = item.judul
    b.tvDetail.text = item.detail
    b.ivIcon.setImageResource(iconRes)
    if (item.status.isBlank()) b.tvStatus.visibility = View.GONE else b.tvStatus.text = item.status
    if (item.waktu.isBlank()) b.tvWaktu.visibility = View.GONE else b.tvWaktu.text = item.waktu
    if (!showDivider) b.divider.visibility = View.GONE
    b.root.setOnClickListener { onClick?.invoke() }
    addView(b.root)
}

fun String.toDisplayName(): String =
    substringBefore("@").ifBlank { "Pengguna" }.replaceFirstChar { it.uppercase() }