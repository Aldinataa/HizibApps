package com.example.cobain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cobain.model.ListAyat
import com.example.cobain.databinding.ListAyatBinding

class AyatAdapter(private val ayat: List<ListAyat>): RecyclerView.Adapter<AyatAdapter.AyatViewHolder>() {

    class AyatViewHolder(private val binding: ListAyatBinding): RecyclerView.ViewHolder(binding.root) {
        val ayat = binding.tvAyat
        val arti = binding.tvArti
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyatViewHolder {
        val binding = ListAyatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AyatViewHolder(binding)
    }

    override fun getItemCount(): Int = ayat.size

    override fun onBindViewHolder(holder: AyatViewHolder, position: Int) {
        val data = ayat[position]
        holder.ayat.text = data.ayat
        holder.arti.text = data.arti
    }
}