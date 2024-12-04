package com.example.cobain.Activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cobain.R
import com.example.cobain.adapter.AdapterHizib
import com.example.cobain.data.DataHizib
import com.example.cobain.databinding.ActivityMembacaHizibBinding
import com.example.cobain.model.ListHizib

class MembacaHizibActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMembacaHizibBinding
    private var currentTextSize = 24f
    private lateinit var adapter: AdapterHizib
    private val allItems: List<ListHizib> = DataHizib.hizibb

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMembacaHizibBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        
        // Set RecyclerView
        binding.rvHizib.layoutManager = LinearLayoutManager(this)
        adapter = AdapterHizib(allItems, currentTextSize)
        binding.rvHizib.adapter = adapter

        // Langsung set mode "Ikhtisar" sebagai mode default
        adapter.setMode("ikhtisar")

        // Set klik listener untuk memperbesar teks
        binding.ivIncreaseSize.setOnClickListener {
            currentTextSize += 2f
            adapter.updateTextSize(currentTextSize)
        }

        // Set klik listener untuk memperkecil teks
        binding.ivDecreaseSize.setOnClickListener {
            if (currentTextSize > 10f) {
                currentTextSize -= 2f
                adapter.updateTextSize(currentTextSize)
            }
        }

        // Setup Spinner
        val titles = allItems.map { it.judul }
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, titles)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        // Set listener untuk panah
        binding.arrowButton.setOnClickListener {
            showOptionsDialog()
        }
    }

    private fun showOptionsDialog() {
        val options = arrayOf("Isi", "Mode")
        AlertDialog.Builder(this)
            .setTitle("Pilih Opsi")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> showListDialog() // "Isi" option
                    1 -> showModeDialog() // "Mode" option
                }
            }
            .show()
    }

    private fun showListDialog() {
        val titles = allItems.map { it.judul }
        AlertDialog.Builder(this)
            .setTitle("Pilih Judul")
            .setItems(titles.toTypedArray()) { _, which ->
                binding.rvHizib.smoothScrollToPosition(which)
            }
            .show()
    }

    private fun showModeDialog() {
        val modes = arrayOf("Ikhtisar", "Nahdlatul Watan", "Nahdlatul Banat")
        AlertDialog.Builder(this)
            .setTitle("Pilih Mode")
            .setItems(modes) { _, which ->
                when (which) {
                    0 -> adapter.setMode("ikhtisar")
                    1 -> adapter.setMode("nahdlatul watan")
                    2 -> adapter.setMode("nahdlatul banat")
                }
            }
            .show()
    }
    }
