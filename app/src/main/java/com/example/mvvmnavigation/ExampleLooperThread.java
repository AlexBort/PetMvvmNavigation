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
  //    handler = new Handler(); // in such case we will get crash, because:  java.lang.RuntimeException: Can't create handler inside thread Thread[Thread-2,5,main] that has not called Looper.prepare()
    /**
     * Handler is working with Looper and MessageQuee
     * therefore we will init Handler with Looper:
     */
    Looper.prepare(); // it 1- will add looper to this background thread and 2- and it automatically create the message queue
    handler = new Handler();
    Looper.loop(); // we will be in infinite for Loop ()

    Log.v(TAG, "run(): " + "end"); // when operations of method run() will be finished Thread will be destroyed
  }
}
