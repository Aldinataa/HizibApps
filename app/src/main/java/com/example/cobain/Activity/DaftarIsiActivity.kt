package com.example.cobain.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cobain.R
import com.example.cobain.adapter.AdapterList
import com.example.cobain.adapter.ListIsiAdapter
import com.example.cobain.data.DataHizib
import com.example.cobain.databinding.ActivityDaftarIsiBinding
import com.example.cobain.model.ListIsi

class DaftarIsiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDaftarIsiBinding
    private  var listJudul : ArrayList<ListIsi> = arrayListOf()
    private var filteredList: List<ListIsi> = listJudul

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDaftarIsiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvIsi.layoutManager = LinearLayoutManager(this)
        binding.rvIsi.adapter = ListIsiAdapter(DataHizib.hizib)

        listJudul.addAll(getList()) // Mengisi data awal ke listJudul
        val adapter = ListIsiAdapter(listJudul)
        binding.rvIsi.layoutManager = LinearLayoutManager(this)
        binding.rvIsi.adapter = adapter

        // Setup Search View
        with(binding) {
            viewsearch.setupWithSearchBar(searchBar)
            viewsearch.editText.setOnEditorActionListener { _, _, _ ->
                searchBar.setText(viewsearch.text)
                val searchQuery = searchBar.text.toString()
                viewsearch.hide()
                filterList(searchQuery, adapter)
                false
            }
        }
    }
    // Fungsi untuk memfilter list berdasarkan query
    private fun filterList(query: String, adapter: ListIsiAdapter) {
        filteredList = if (query.isEmpty()) {
            listJudul
        } else {
            listJudul.filter { item ->
                item.judul.contains(query, true)
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
        }

        adapter.updatelist(filteredList)
    }

    // Fungsi untuk mendapatkan list data
    private fun getList(): ArrayList<ListIsi> {
        return ArrayList(DataHizib.hizib)
    }
}