package com.example.mvvmnavigation.work_manager

import androidx.work.WorkManager
import androidx.work.Worker
import java.util.*

class Test {

    fun testLib(){
        WorkManager.getInstance()
        .beginWith(Arrays.asList(workA, workB))
            .then(workC)
            .enqueue();

        WorkManager.getInstance().getStatusById(myWorkRequest.getId())
            .observe(this, object : Observer<WorkStatus?>() {
                fun onChanged(@Nullable workStatus: WorkStatus) {
                    Log.d(TAG, "onChanged: " + workStatus.getState())
                }
            })

    }

}