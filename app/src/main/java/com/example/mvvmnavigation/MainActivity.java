package com.example.mvvmnavigation;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final TextView text = findViewById(R.id.text);
    Button button = findViewById(R.id.launch_thread);

    handler = new Handler(Looper.myLooper()) {
      @Override
      public void handleMessage(@NonNull Message msg) {
        switch (msg.what) {
          case STATUS_NONE:
            text.setText("Not connected");
            break;
          case STATUS_CONNECTING:
            text.setText("Connecting");
            break;
          case STATUS_CONNECTED:
            text.setText("Connected");
            break;
        }
      }
    };

    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              handler.sendEmptyMessage(STATUS_CONNECTING);
              TimeUnit.SECONDS.sleep(2);
              // установлено
              handler.sendEmptyMessage(STATUS_CONNECTED);
              // выполняется какая-то работа
              TimeUnit.SECONDS.sleep(3);
              // разрываем подключение
              handler.sendEmptyMessage(STATUS_NONE);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        });
      }
    });
  }
}