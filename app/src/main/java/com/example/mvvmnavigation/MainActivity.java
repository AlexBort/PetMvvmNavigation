package com.example.mvvmnavigation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.mvvmnavigation.databinding.ActivityMainBinding;
import com.example.mvvmnavigation.databinding.PartResultBinding;

public class MainActivity extends AppCompatActivity {

  ActivityMainBinding mainBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(mainBinding.getRoot());
  }

  public PartResultBinding getPartResultLayout(){
    return PartResultBinding.bind(mainBinding.getRoot());
  }

}