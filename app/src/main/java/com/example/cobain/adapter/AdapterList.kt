package com.example.cobain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cobain.R
import com.example.cobain.databinding.ListItemBinding
import com.example.cobain.model.ListItem

class AdapterList(private var list : List<ListItem>) : RecyclerView.Adapter<AdapterList.ViewHolder>(){

    private lateinit var mhizib : onItemClickListener


    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mhizib = listener
    }

    class ViewHolder(itemview: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemview) {
        private val binding = ListItemBinding.bind(itemview)
        val judul = binding.tvJudul

        init {
            itemview.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view, mhizib)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.judul.text=item.judul
    }

}