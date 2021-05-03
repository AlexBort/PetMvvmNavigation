package com.example.mvvmnavigation;

import static com.example.mvvmnavigation.ExampleHandler.TASK_A;
import static com.example.mvvmnavigation.ExampleHandler.TASK_B;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

  private Handler handler;
  final int STATUS_NONE = 0; // нет подключения
  final int STATUS_CONNECTING = 1; // подключаемся
  final int STATUS_CONNECTED = 2; // подключено
  private static final String TAG = "MainActivity";
  ExampleLooperThread looperThread = new ExampleLooperThread();
  Button button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    button = findViewById(R.id.button_start);
  }


  /**
   *
   * we try to start thread again, it will not be possible
   *
   */
  public void startThread(View view) {
    looperThread.start();
  }

  public void stopThread(View view) {
    looperThread.looper.quit();
  }

  public void taskA(View view) {
    Message msg = Message.obtain();
    msg.what = TASK_A;
    looperThread.handler.sendMessage(msg);
  }

  public void taskB(View view) {
    Message msg = Message.obtain();
    msg.what = TASK_B;
    looperThread.handler.sendMessage(msg);
  }
}