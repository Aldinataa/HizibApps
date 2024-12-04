package com.example.cobain.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cobain.databinding.ListDaftarisiBinding
import com.example.cobain.details.DetailisiActivity
import com.example.cobain.model.ListIsi
import com.example.cobain.databinding.ListItemBinding
import java.util.ArrayList

class ListIsiAdapter(private var judul : List<ListIsi>): RecyclerView.Adapter<ListIsiAdapter.ViewHolder>() {

    fun updatelist(newlist : List<ListIsi>){
        judul = newlist
        notifyDataSetChanged()
    }

    class ViewHolder(binding: ListDaftarisiBinding) : RecyclerView.ViewHolder(binding.root) {
        val judul = binding.tvJudul1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListDaftarisiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = judul.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = judul[position]
        holder.judul.text = item.judul

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailisiActivity::class.java).apply {
                putExtra("JUDUL", item.judul)
                putParcelableArrayListExtra("AYAT_LIST", ArrayList(item.ayat))
            }
            context.startActivity(intent)
        }
    }
}