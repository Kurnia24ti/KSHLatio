@file:Suppress("PackageName")

package com.example.KSHLatio.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ksh_latio.R
import com.example.ksh_latio.databinding.ItemPanenCardBinding

class PanenAdapter(
    private val items: List<PanenModel>,
    private val onItemClick: (PanenModel) -> Unit
) : RecyclerView.Adapter<PanenAdapter.PanenViewHolder>() {

    class PanenViewHolder(val binding: ItemPanenCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PanenViewHolder {
        val binding = ItemPanenCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PanenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PanenViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvPanenTitle.text = item.title
            tvPanenDesc.text = item.desc
            tvPanenStatus.text = item.status
            tvPanenDate.text = item.date
            ivPanenImage.setImageResource(R.drawable.ic_eco)

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}