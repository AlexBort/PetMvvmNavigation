package com.example.mvvmnavigation;


import static com.example.mvvmnavigation.TestService.EXTRA_URL;

import android.content.Intent;
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



    Intent intentService = new Intent(this, TestService.class);
    intentService.setAction(ACTION_DOWNLOAD_FILE);
    intentService.putExtra(EXTRA_URL,"google.com");
    startService(intentService);

  }
}