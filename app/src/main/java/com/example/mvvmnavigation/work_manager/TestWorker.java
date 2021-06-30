package com.example.mvvmnavigation.work_manager;

import androidx.annotation.NonNull;
import androidx.work.Worker;

import org.jetbrains.annotations.NotNull;

public class TestWorker extends Worker {


    @NonNull
    @NotNull
    @Override
    public Result doWork() {
        // there is we can do some async background work
        // under the hood work thread is launched used java EXECUTOR
        return null;
    }
}
