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
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

  private Handler handler;
  final int STATUS_NONE = 0; // нет подключения
  final int STATUS_CONNECTING = 1; // подключаемся
  final int STATUS_CONNECTED = 2; // подключено
  final int STATUS_DOWNLOAD_START = 3; // загрузка началась
  final int STATUS_DOWNLOAD_FILE = 4; // файл загружен
  final int STATUS_DOWNLOAD_END = 5; // загрузка закончена
  final int STATUS_DOWNLOAD_NONE = 6; // нет файлов для загрузки

  TextView tvStatus;
  ProgressBar pbDownload;
  Button btnConnect;

  private static final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    tvStatus = (TextView) findViewById(R.id.tvStatus);
    pbDownload = (ProgressBar) findViewById(R.id.pbDownload);
    btnConnect = (Button) findViewById(R.id.btnConnect);

    handler = new Handler(Looper.myLooper()) {
      public void handleMessage(android.os.Message msg) {
        switch (msg.what) {
          case STATUS_NONE:
            btnConnect.setEnabled(true);
            tvStatus.setText("Not connected");
            pbDownload.setVisibility(View.GONE);
            break;
          case STATUS_CONNECTING:
            btnConnect.setEnabled(false);
            tvStatus.setText("Connecting");
            break;
          case STATUS_CONNECTED:
            tvStatus.setText("Connected");
            break;
          case STATUS_DOWNLOAD_START:
            tvStatus.setText("Start download " + msg.arg1 + " files");
            pbDownload.setMax(msg.arg1);
            pbDownload.setProgress(0);
            pbDownload.setVisibility(View.VISIBLE);
            break;
          case STATUS_DOWNLOAD_FILE:
            tvStatus.setText("Downloading. Left " + msg.arg2 + " files");
            pbDownload.setProgress(msg.arg1);
            saveFile((byte[]) msg.obj);
            break;
          case STATUS_DOWNLOAD_END:
            tvStatus.setText("Download complete!");
            break;
          case STATUS_DOWNLOAD_NONE:
            tvStatus.setText("No files for download");
            break;
        }
      }

      ;
    };
    handler.sendEmptyMessage(STATUS_NONE);
  }


  public void onclick(View view) {
    Thread t = new Thread(new Runnable() {
      Message msg;
      byte[] file;
      Random rand = new Random();

      public void run() {
        try {
          // устанавливаем подключение
          handler.sendEmptyMessage(STATUS_CONNECTING);
          TimeUnit.SECONDS.sleep(1);

          // подключение установлено
          handler.sendEmptyMessage(STATUS_CONNECTED);

          // определяем кол-во файлов
          TimeUnit.SECONDS.sleep(1);
          int filesCount = rand.nextInt(5);

          if (filesCount == 0) {
            // сообщаем, что файлов для загрузки нет
            handler.sendEmptyMessage(STATUS_DOWNLOAD_NONE);
            // и отключаемся
            TimeUnit.MILLISECONDS.sleep(1500);
            handler.sendEmptyMessage(STATUS_NONE);
            return;
          }

          // загрузка начинается
          // создаем сообщение, с информацией о количестве файлов
          msg = handler.obtainMessage(STATUS_DOWNLOAD_START, filesCount, 0);
          Message message = Message.obtain();

          // отправляем
          handler.sendMessage(msg);

          for (int i = 1; i <= filesCount; i++) {
            // загружается файл
            file = downloadFile();
            // создаем сообщение с информацией о порядковом номере
            // файла,
            // кол-вом оставшихся и самим файлом
            msg = handler.obtainMessage(STATUS_DOWNLOAD_FILE, i,
                filesCount - i, file);
            // отправляем
            handler.sendMessage(msg);
          }

          // загрузка завершена
          handler.sendEmptyMessage(STATUS_DOWNLOAD_END);

          // отключаемся
          TimeUnit.MILLISECONDS.sleep(1500);
          handler.sendEmptyMessage(STATUS_NONE);

        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    t.start();
  }

  byte[] downloadFile() throws InterruptedException {
    TimeUnit.SECONDS.sleep(2);
    return new byte[1024];
  }

  void saveFile(byte[] file) {

  }
}