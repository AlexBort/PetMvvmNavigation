package com.example.mvvmnavigation;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  String ACTION_DOWNLOAD_FILE = BuildConfig.APPLICATION_ID + "ACTION_DOWNLOAD_FILE";
  String ACTION_CALCULATE = BuildConfig.APPLICATION_ID + "ACTION_CALCULATE";
  String EXTRA_URL = "EXTRA_URL";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

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
}