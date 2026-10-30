@file:Suppress("PackageName")

package com.example.KSHLatio.util

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.example.ksh_latio.R
import com.example.ksh_latio.databinding.CustomToastBinding

object ToastHelper {

    enum class ToastType {
        SUCCESS, INFO, WARNING, ERROR
    }

    fun showToast(context: Context, message: String, type: ToastType = ToastType.INFO) {
        val inflater = LayoutInflater.from(context)
        val binding = CustomToastBinding.inflate(inflater)

        val (icon, bgRes) = when (type) {
            ToastType.SUCCESS -> Pair("✅", R.drawable.bg_badge)
            ToastType.INFO -> Pair("ℹ️", R.drawable.bg_badge_blue)
            ToastType.WARNING -> Pair("⚠️", R.drawable.bg_pill_white)
            ToastType.ERROR -> Pair("❌", R.drawable.bg_input)
        }

        binding.tvToastIcon.text = icon
        binding.tvToastMessage.text = message
        binding.toastRoot.setBackgroundResource(bgRes)

        Toast(context.applicationContext).apply {
            duration = Toast.LENGTH_SHORT
            @Suppress("DEPRECATION")
            view = binding.root
            show()
        }
    }

    fun showSuccess(context: Context, message: String) = showToast(context, message, ToastType.SUCCESS)
    fun showInfo(context: Context, message: String) = showToast(context, message, ToastType.INFO)
    fun showWarning(context: Context, message: String) = showToast(context, message, ToastType.WARNING)
    fun showError(context: Context, message: String) = showToast(context, message, ToastType.ERROR)
}