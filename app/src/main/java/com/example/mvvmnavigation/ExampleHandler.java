package com.example.mvvmnavigation;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;

public class ExampleHandler extends Handler {

  private static final String TAG = "ExampleHandler"; // logt - HOT KEY
  public static final int TASK_A = 1; // psfi - HOT KEY
  public static final int TASK_B = 2;


  @Override
  public void handleMessage(@NonNull Message msg) {
    switch (msg.what) {
      case TASK_A:
        Log.v(TAG, "taskA executed");
        break;
      case TASK_B:
        Log.v(TAG, "taskB executed");
        break;
    }
  }
}
