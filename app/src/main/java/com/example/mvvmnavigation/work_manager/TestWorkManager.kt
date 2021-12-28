package com.example.mvvmnavigation.work_manager

import android.content.Context
import androidx.core.content.FileProvider
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager

class TestWorkManager(context: Context): CoroutineWorker(context,null) {

    override suspend fun doWork(): Result {
FileProvider
        if (Result.failure())


        TODO("Not yet implemented")
    }

}