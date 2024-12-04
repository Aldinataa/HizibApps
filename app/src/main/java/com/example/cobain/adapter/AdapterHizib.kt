package com.example.cobain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cobain.R
import com.example.cobain.databinding.ListHizibBinding
import com.example.cobain.model.ListHizib

class AdapterHizib (private var hizib: List<ListHizib>, private var textSize: Float) : RecyclerView.Adapter<AdapterHizib.ViewHolder>() {

    private var mode = "ikhtisar" // Mode default adalah 'ikhtisar'
    private var filteredHizib: List<ListHizib> = hizib

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        private val binding = ListHizibBinding.bind(itemview)
        val judul = binding.tvJudul
        val ayat = binding.tvAyat
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_hizib, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int = filteredHizib.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = filteredHizib[position]
        holder.judul.text = data.judul
        holder.ayat.text = data.ayat

        holder.judul.textSize = textSize
        holder.ayat.textSize = textSize
    }
    // Fungsi untuk memperbarui ukuran teks
    fun updateTextSize(newSize: Float) {
        textSize = newSize
        notifyDataSetChanged()
    }
    // Fungsi untuk memperbarui data yang ditampilkan
    fun updateItems(newItems: List<ListHizib>) {
        this.hizib = newItems
        applyFilter()
        notifyDataSetChanged()
    }
    // Fungsi untuk mengatur mode tampilan
    fun setMode(newMode: String) {
        mode = newMode
        applyFilter()
        notifyDataSetChanged()
    }

    // Fungsi untuk menerapkan filter sesuai mode
    private fun applyFilter() {
        filteredHizib = when (mode) {
            "ikhtisar" -> hizib.filterIndexed{ index, _ -> index != 9 && index != 10 }
            "nahdlatul watan" -> hizib.filterIndexed { index, _ -> index != 8 && index != 10 }
            "nahdlatul banat" -> hizib.filterIndexed { index, _ -> index != 8 && index != 9 }
            else -> hizib
        }
    }
}