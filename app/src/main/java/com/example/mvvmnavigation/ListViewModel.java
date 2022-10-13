package com.example.mvvmnavigation;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import com.example.mvvmnavigation.api.ApiConnector;
import com.example.mvvmnavigation.api.ManResponse;
import com.example.mvvmnavigation.api.ResponseCallback;
import com.example.mvvmnavigation.models.User;
import java.util.List;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;

public class ListViewModel extends ViewModel implements LifecycleObserver {

  private final MutableLiveData<List<String>> data = new MutableLiveData<>();
  private final MutableLiveData<String> dataString = new MutableLiveData<>();
  private final MutableLiveData<List<User>> users = new MutableLiveData<>();

  public MutableLiveData<String> getDataString() {
    return dataString;
  }

  public MutableLiveData<List<User>> getUsersList() {
    return users;
  }

  public LiveData<List<String>> getData() {
    return data;
  }

  @OnLifecycleEvent(Event.ON_START)
  public void onViewStarted() {
    new ApiConnector().sendRequestInfoList(new ResponseCallback<ManResponse>() {
      @Override
      public void onSuccess(ManResponse response) {

//        Handler(Looper.getMainLooper())

        users.setValue(response.getResults());
      }

      @Override
      public void onError(@NotNull Throwable throwable) {

      }
    });
//    List<String> list = App.repository.getTestList();
//    data.setValue(list);
//    Log.d("ListViewModel", "onViewStarted");
//
//    dataString.setValue("TEST VALUE");
  }

}
