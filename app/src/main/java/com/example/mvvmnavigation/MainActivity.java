package com.example.mvvmnavigation;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TaskListener {

  private static final String TAG = "MainActivity";

  String ACTION_DOWNLOAD_FILE = BuildConfig.APPLICATION_ID + "ACTION_DOWNLOAD_FILE";
  String ACTION_CALCULATE = BuildConfig.APPLICATION_ID + "ACTION_CALCULATE";
  String EXTRA_URL = "EXTRA_URL";
  private App app;
  private TextView textProgress;
  private ProgressBar progressBar;

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
        Intent intentService = new Intent(MainActivity.this, TestService.class);
        intentService.setAction(ACTION_DOWNLOAD_FILE);
        intentService.putExtra(EXTRA_URL, "google.com");
        startService(intentService);

      }
    });


  }

  @Override
  protected void onStart() {
    super.onStart();
    app.addListener(this);
  }

  @Override
  protected void onStop() {
    super.onStop();
    app.removeListener(this);
  }

  @Override
  public void onProgressChanged(int percents) {
    progressBar.setVisibility(View.VISIBLE);
    textProgress.setVisibility(View.VISIBLE);
    textProgress.setText(getString(R.string.percents, percents));// форма, при которой проценьы будут меняться, хотя берем из стрингов значение
//    app.
  }

  @Override
  public void onCompleted() {
    progressBar.setVisibility(View.GONE);
    textProgress.setVisibility(View.GONE);
  }
}