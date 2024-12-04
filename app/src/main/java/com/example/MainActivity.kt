package com.example.cobain.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cobain.adapter.AdapterList
import com.example.cobain.data.DataList
import com.example.cobain.databinding.ActivityMainBinding
import com.example.cobain.model.ListItem
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvList : RecyclerView
    private  var listJudul : ArrayList<ListItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        rvList = binding.rvList
        rvList.setHasFixedSize(true)
        listJudul.addAll(getList())
        setRecyclerView()
    }

    private fun setRecyclerView() {
        rvList.layoutManager = GridLayoutManager(this, 2)
        val listAdepter = AdapterList(listJudul)
        rvList.adapter = listAdepter

        listAdepter.setOnItemClickListener(object : AdapterList.onItemClickListener{
            override fun onItemClick(position:Int){
                val item = DataList.itemlist[position]

                val intent = Intent(this@MainActivity, when(item.judul){
                    "Membaca Hizib" -> MembacaHizibActivity::class.java
                    "Daftar Isi" -> DaftarIsiActivity::class.java
                    "Audio" -> AudioActivity::class.java
                    "Kaidah Membaca" -> KaidahMembacaActivity::class.java
                    "Kata Pengantar" -> KataPengantarActivity::class.java
                    "Tentang Aplikasi" -> TentangAplikasiActivity::class.java

                    else -> throw IllegalArgumentException("Data Kosong")
                })
                startActivity(intent)
            }
        })
    }

    private fun getList(): ArrayList<ListItem> {
        val list = ArrayList<ListItem>()
        for (item in DataList.itemlist){
            list.add(item)
        }
        return list
    }
}