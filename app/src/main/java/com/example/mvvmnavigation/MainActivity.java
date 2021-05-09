package com.example.mvvmnavigation;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.mvvmnavigation.services.TestBindingService;
import com.example.mvvmnavigation.services.TestBindingService.TestBinder;
import com.example.mvvmnavigation.services.TestIntentService;
import com.example.mvvmnavigation.services.TestService;

public class MainActivity extends AppCompatActivity /*implements TaskListener*/ {

  private static final String TAG = "MainActivity";

  String ACTION_DOWNLOAD_FILE = BuildConfig.APPLICATION_ID + "ACTION_DOWNLOAD_FILE";
  String ACTION_CALCULATE = BuildConfig.APPLICATION_ID + "ACTION_CALCULATE";
  String EXTRA_URL = "EXTRA_URL";
  private App app;
  private TextView textProgress;
  private ProgressBar progressBar;
  private TestBindingService bindingService; // такая служба существует в одном экзмепляре

  ServiceConnection connection = new ServiceConnection() {

    // бвудет вызван при успешной инициализации привязки нашей службы
    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
      bindingService = ((TestBinder) binder).getService();
    }

    // этот метод может быть нужен только, если мы работаем с несколькими процеесами
    @Override
    public void onServiceDisconnected(ComponentName name) {
      bindingService = null;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    app = (App) getApplication();

    textProgress = findViewById(R.id.progress_text);
    progressBar = findViewById(R.id.progress);

    findViewById(R.id.buttonClcl).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intentService = new Intent(MainActivity.this, TestService.class);
        intentService.setAction(ACTION_CALCULATE);
//        intentService.putExtra(EXTRA_URL, "google.com");
        startService(intentService);
      }
    });

    findViewById(R.id.buttonDwn).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (bindingService == null) {
          return;
        }
        String settings = bindingService.getSettings();
        bindingService.downloadFile("URL", new TaskListener() {
          @Override
          public void onProgressChanged(int percents) {
            progressBar.setVisibility(View.VISIBLE);
            textProgress.setVisibility(View.VISIBLE);
            textProgress.setText(getString(R.string.percents,
                percents));
          }

          @Override
          public void onCompleted() {
            progressBar.setVisibility(View.GONE);
            textProgress.setVisibility(View.GONE);
          }
        });

        Toast.makeText(MainActivity.this, "settings: " + settings, Toast.LENGTH_SHORT).show();

      }
    });

    /* Есть 2 способа привязывания:
    - с автоматическим созданием службы
    - с ручным созданием службы
     */
    Intent intent = new Intent(MainActivity.this, TestBindingService.class);
    startService(intent);

  }

  @Override
  protected void onStart() {
    super.onStart();
    Intent intent = new Intent(this, TestBindingService.class);
    bindService(intent, connection, BIND_AUTO_CREATE);
  }

  @Override
  protected void onStop() {
    super.onStop();
    unbindService(connection);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (isDestroyed()) {
      Intent intent = new Intent(MainActivity.this, TestBindingService.class);
      stopService(intent);
    }
  }

  //  @Override
//  public void onProgressChanged(int percents) {
//    progressBar.setVisibility(View.VISIBLE);
//    textProgress.setVisibility(View.VISIBLE);
//    textProgress.setText(getString(R.string.percents,
//        percents));// форма, при которой проценьы будут меняться, хотя берем из стрингов значение
////    app.
//  }
//
//  @Override
//  public void onCompleted() {
//    progressBar.setVisibility(View.GONE);
//    textProgress.setVisibility(View.GONE);
//  }
//
//  class TestBinder extends Binder {
//    MainActivity getService() {
//      return MainActivity.this;
//    }
//  }

}