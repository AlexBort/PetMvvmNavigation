package com.example.mvvmnavigation;

import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;

public class CustomHandler extends Handler {

//  public CustomHandler() {
//    new CustomHandler(){
//      @Override
//      public void handleMessage(@NonNull Message msg) {
//        super.handleMessage(msg);
//      }
//    };
//  }


  @Override
  public void handleMessage(@NonNull android.os.Message msg) {
    super.handleMessage(msg);
//    handle(msg);
  }

  private void handle(Message message) {
    switch (message) {
      case WAIT:
      case PREPARE:
    }
  }


  enum Message {
    WAIT, PREPARE
  }

}
