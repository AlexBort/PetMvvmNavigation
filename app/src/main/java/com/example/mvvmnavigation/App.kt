package com.example.mvvmnavigation;

import android.app.Application;

public class App extends Application {

  public static Repository repository = new Repository();

  @Override
  public void onCreate() {
    super.onCreate();
//    Repository repository = new Repository();
  }
}
