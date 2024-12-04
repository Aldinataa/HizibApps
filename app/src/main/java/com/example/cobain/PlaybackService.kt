package com.example.cobain

import android.app.Service
import android.content.Intent
import android.os.IBinder

import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.exoplayer.ExoPlayer

class PlaybackService : MediaSessionService() {
    private var mediaSession: MediaSession? = null

    override fun onCreate() {
        super.onCreate()
        initializeSessionAndPlayer()
    }

    private fun initializeSessionAndPlayer() {
        val player = ExoPlayer.Builder(this).build()


        val audioItems = listOf(
            createMediaItem(R.raw.miftah, "Miftah"),
            createMediaItem(R.raw.surahyasin, "Surah Yasin"),
            createMediaItem(R.raw.katapengantar, "Kata Pengantar"),
            createMediaItem(R.raw.sholawatnariyah, "Sholawat Nariyah"),
            createMediaItem(R.raw.sholawatnahdlatin, "Sholawat Nahdlatain"),
            createMediaItem(R.raw.doa, "Doa"),
            createMediaItem(R.raw.amrulmutaqab, "Amrulmutaqab"),
            createMediaItem(R.raw.doapenutup, "Doa Penutup"),
            createMediaItem(R.raw.ikhtisarhizib, "Ikhtisar Hizib"),
            createMediaItem(R.raw.imamabdulqasim, "Imam Abdul Qasim"),
            createMediaItem(R.raw.syayidatulaulia, "Syaiyat Ul'Aulia"),
            createMediaItem(R.raw.katapengantar, "Kata Pengantar"),
            createMediaItem(R.raw.sholawatthib, "Sholawat Thib"),
            createMediaItem(R.raw.sitiaisah, "Siti Aisyah"),
            createMediaItem(R.raw.pujiansyaidmuhammadamin, "Pujian Syayid Muhammad amin"),
        )

        // Add items to the player
        audioItems.forEach { player.addMediaItem(it) }

        player.prepare()
        mediaSession = MediaSession.Builder(this, player).build()
    }

    private fun createMediaItem(resId: Int, title: String): MediaItem {
        return MediaItem.Builder()
            .setUri("android.resource://${packageName}/$resId")
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setTitle(title)
                    .build()
            ).build()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }
}
