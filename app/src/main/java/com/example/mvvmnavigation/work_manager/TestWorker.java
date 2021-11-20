package com.example.mvvmnavigation.work_manager;

import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.job.JobScheduler;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.jetbrains.annotations.NotNull;

public class TestWorker extends Worker {


    public TestWorker(@NonNull @NotNull Context context, @NonNull @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @NotNull
    @Override
    public Result doWork() {
//        DownloadManager


        // there is we can do some async background work
        // under the hood work thread is launched used java EXECUTOR
        return null;
    }
}
