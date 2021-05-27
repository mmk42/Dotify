package edu.uw.mmk42.dotify.manager

import android.content.Context
import androidx.work.*
import edu.uw.mmk42.dotify.SongSyncWorker
import java.sql.Time
import java.util.concurrent.TimeUnit

private const val SONG_SYNC_WORK_TAG = "SONG_SYNC_WORK_TAG"

class SongSyncManager(context: Context) {
    private val workManager: WorkManager = WorkManager.getInstance(context)

    fun syncSongs() {

        val request = OneTimeWorkRequestBuilder<SongSyncWorker>()
            .setInitialDelay(5,TimeUnit.SECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .addTag(SONG_SYNC_WORK_TAG)
            .build()
        workManager.enqueue(request)

    }

    fun syncSongsPeriodically() {

        if(isSongSyncRunning()) {
            return
        }
        val request = PeriodicWorkRequestBuilder<SongSyncWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5,TimeUnit.SECONDS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .addTag(SONG_SYNC_WORK_TAG)
            .build()

        workManager.enqueue(request)
    }

    fun stopPeriodicallySyncing() {
        workManager.cancelAllWorkByTag(SONG_SYNC_WORK_TAG)
    }

    private fun isSongSyncRunning(): Boolean {
        return workManager.getWorkInfosByTag(SONG_SYNC_WORK_TAG).get().any {
            when(it.state) {
                WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
                else -> false
            }
        }
    }

}
