package edu.uw.mmk42.dotify

import android.app.Application
import android.util.Log
import android.widget.Toast
import edu.uw.mmk42.dotify.manager.MusicManager
import edu.uw.mmk42.dotify.manager.SongSyncManager
import edu.uw.mmk42.dotify.repository.UserRepository

class DotifyApplication: Application() {


    lateinit var userRepository: UserRepository

    //val musicManager: MusicManager by lazy {MusicManager()}
    lateinit var musicManager: MusicManager

    lateinit var songSyncManager: SongSyncManager
    lateinit var songNotificationManager: SongNotificationManager

    override fun onCreate() {
        super.onCreate()
        userRepository = UserRepository()
        musicManager = MusicManager()

        this.songSyncManager = SongSyncManager(this)

        this.songNotificationManager = SongNotificationManager(this)


        Log.i("dotifyApp", "Number of songs clicked is" + musicManager.getNumOfSongsClicked())

        //Toast.makeText(this,"Application has booted", Toast.LENGTH_SHORT).show()
    }
}