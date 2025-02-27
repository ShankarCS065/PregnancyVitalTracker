package com.devlopershankar.pregnancyvitalstracker.notification

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class ReminderWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        NotificationHelper.sendNotification(applicationContext)
        return Result.success()
    }
}
