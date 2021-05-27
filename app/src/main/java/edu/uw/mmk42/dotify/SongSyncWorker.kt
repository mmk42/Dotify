package edu.uw.mmk42.dotify

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class SongSyncWorker(
    context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {


    override suspend fun doWork(): Result {
        Log.i("SongSYncWOrker", "syncing songs now")

        return Result.success()
    }

}