package edu.uw.mmk42.dotify

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ericchee.songdataprovider.Song
import kotlin.random.Random

private const val NEW_SONG_CHANNEL_ID =  "NEW_SONG_CHANNEL_ID "

class SongNotificationManager(
    private val context: Context
) {
    val songNotificationManager = NotificationManagerCompat.from(context)

    init {
        // initialize all channels
        initNotificationChannels()
    }

    fun publishNewSongNotification(song: Song) {
        // define the intent or action you want when uer taps on notification
        val intent = Intent(context, PlayerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(SONG_KEY, song)
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        // build information you want the notification to show
        val builder = NotificationCompat.Builder(context, NEW_SONG_CHANNEL_ID) // channel id from creating the channel
            .setSmallIcon(R.drawable.ic_baseline_music_note)
            .setContentTitle("${song.artist} just released a new song!!!")
            .setContentText("Listen to ${song.title} now on Dotify")
            .setContentIntent(pendingIntent) // sets the action when user clicks on notification
            .setAutoCancel(true) // dismiss notification on tap
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        // tell os to publish the notification
        with(songNotificationManager) {
            // notificationId is a unique int for each notification that you must define
            val notificationId = Random.nextInt()
            notify(notificationId, builder.build())
        }
    }

    private fun initNotificationChannels() {
        newSongsChannel()
        // can add more channels here
    }

    private fun newSongsChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Info about channel
            val name = context.getString(R.string.new_songs)
            val descriptionText = context.getString(R.string.new_songs_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            // create channel object
            val channel = NotificationChannel(NEW_SONG_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Tell the android operating system to create a channel
            songNotificationManager.createNotificationChannel(channel)
        }
    }

}