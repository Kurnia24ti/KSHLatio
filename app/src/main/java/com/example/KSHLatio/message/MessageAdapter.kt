@file:Suppress("PackageName")

package com.example.KSHLatio.message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.ksh_latio.R
import com.example.ksh_latio.databinding.ItemMessageBinding
import com.google.android.material.snackbar.Snackbar

class MessageAdapter(
    context: Context,
    private val messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root

        val data = messages[position]

        if (data.avatarUrl.isNotBlank()) {
            Glide.with(context)
                .load(data.avatarUrl)
                .placeholder(R.drawable.ic_eco)
                .error(R.drawable.ic_eco)
                .into(binding.avatarImg)
        } else {
            binding.avatarImg.setImageResource(R.drawable.ic_eco)
        }

        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText

        view.setOnClickListener {
            Snackbar.make(
                parent,
                "Pesan dari ${data.senderName}: ${data.messageText}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}