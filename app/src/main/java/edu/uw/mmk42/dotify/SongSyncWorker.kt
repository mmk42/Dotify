package edu.uw.mmk42.dotify

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ericchee.songdataprovider.SongDataProvider

class SongSyncWorker(
    private val context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    private val application by lazy {context.applicationContext as DotifyApplication}
    private val songNotificationManager by lazy {application.songNotificationManager}
    override suspend fun doWork(): Result {


        Log.i("SongSyncWorker", "syncing songs now")

        try {
            songNotificationManager.publishNewSongNotification(SongDataProvider.getAllSongs().random())
            return Result.success()
        } catch (ex: Exception) {
            return Result.failure()
        }



    }

}