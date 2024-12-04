package com.example.cobain.details

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cobain.Activity.AudioActivity
import com.example.cobain.Activity.KaidahMembacaActivity
import com.example.cobain.Activity.KataPengantarActivity
import com.example.cobain.Activity.MembacaHizibActivity
import com.example.cobain.Activity.TentangAplikasiActivity
import com.example.cobain.R
import com.example.cobain.adapter.AyatAdapter
import com.example.cobain.databinding.ActivityDetailisiBinding
import com.example.cobain.model.ListAyat
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar

class DetailisiActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityDetailisiBinding
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailisiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Dapatkan data yang dikirim dari MainActivity
        val judul = intent.getStringExtra("JUDUL") ?: "Judul Tidak Ditemukan"
        val ayatList = intent.getParcelableArrayListExtra<ListAyat>("AYAT_LIST") ?: arrayListOf()

        // Atur judul
        binding.tvJudul.text = judul

        // Set up RecyclerView untuk ayat
        binding.rvAyat.layoutManager = LinearLayoutManager(this)
        binding.rvAyat.adapter = AyatAdapter(ayatList)

        // Setup Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        // Set up toolbar
        setSupportActionBar(binding.toolbar) // Mengatur toolbar sebagai ActionBar
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Setup Navigation Drawer
        setupNavigationDrawer()

    }
    private fun setupNavigationDrawer() {
        drawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navView.setNavigationItemSelectedListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_membacahizib -> {
                val inten = Intent(this, MembacaHizibActivity::class.java)
                startActivity(inten)
            }
            R.id.nav_audio -> {
                val inten = Intent(this, AudioActivity::class.java)
                startActivity(inten)
            }
            R.id.nav_ktpengantar -> {
                val inten = Intent(this, KataPengantarActivity::class.java)
                startActivity(inten)
            }
            R.id.nav_kaidahmembaca -> {
                val inten = Intent(this, KaidahMembacaActivity::class.java)
                startActivity(inten)
            }
            R.id.nav_about -> {
                val inten = Intent(this, TentangAplikasiActivity::class.java)
                startActivity(inten)
            }
        }
        binding.drawerLayout.closeDrawers()
        return true
    }
}