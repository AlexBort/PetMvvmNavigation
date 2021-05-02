package com.example.mvvmnavigation;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

public class ExampleLooperThread extends Thread {

  String TAG = getClass().getName();

  private Handler handler; // handler is associated to thread for which it's attached.
  // Therefore there is handler will attach to background thread

  @Override
  public void run() {
    for (int i = 0; i < 5; i++) {
      Log.v(TAG, "run(): " + i);
      SystemClock.sleep(1000);
    }
    Log.v(TAG, "run(): " + "end"); // when operations of method run() will be finished Thread will be destroyed
  }
}
