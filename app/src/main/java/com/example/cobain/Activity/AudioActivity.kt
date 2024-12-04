package com.example.cobain.Activity

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cobain.PlaybackService
import com.example.cobain.R
import com.example.cobain.adapter.ListIsiAdapter
import com.example.cobain.data.DataHizib
import com.example.cobain.databinding.ActivityAudioBinding
import com.google.common.util.concurrent.MoreExecutors

class AudioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAudioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAudioBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        sembunyikanSystemUI()

    }
    override fun onStart() {
        super.onStart()

        // Membuat token untuk menghubungkan ke PlaybackService
        val sessionToken = SessionToken(this, ComponentName(this, PlaybackService::class.java))

        // Membuat MediaController untuk mengontrol playback
        val controllerFuture = MediaController.Builder(this, sessionToken).buildAsync()

        // Menambahkan listener untuk menghubungkan PlayerView dengan MediaController
        controllerFuture.addListener(
            { binding.playerView.player = controllerFuture.get() },
            MoreExecutors.directExecutor()
        )
    }

    private fun sembunyikanSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.playerView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}