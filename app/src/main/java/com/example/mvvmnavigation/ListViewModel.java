package com.example.mvvmnavigation;

import android.util.Log;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import java.util.List;
import java.util.logging.Logger;

public class ListViewModel extends ViewModel implements LifecycleObserver {

  private final MutableLiveData<List<String>> data = new MutableLiveData<>();
  private final MutableLiveData<String> dataString = new MutableLiveData<>();

  public MutableLiveData<String> getDataString() {
    return dataString;
  }

  public LiveData<List<String>> getData() {
    return data;
  }

  @OnLifecycleEvent(Event.ON_START)
  public void onViewStarted() {
    List<String> list = App.repository.getTestList();
    data.setValue(list);
    Log.d("ListViewModel", "onViewStarted");

    dataString.setValue("TEST VALUE");
  }

}
