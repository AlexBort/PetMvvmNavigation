package com.example.mvvmnavigation;

import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;

public class Serv extends IntentService {

  public Serv() {
    super(Serv.class.getSimpleName());
  }

  /**
   * BuildConfig.APPLICATION_ID - вот такой параметр могут добавлять, чтобы этот сервис имел уникальное имя (по названию package-a проекта) И если его захотят вызвать из другого приложения, чтобы это
   * было возможно сделать благодаря тому, что имя action не совпадет с каким-то    другим
   */
  public static final String ACTION_DOWNLOAD_FILE = BuildConfig.APPLICATION_ID + "ACTION_DOWNLOAD_FILE";
  public static final String ACTION_CALCULATE = BuildConfig.APPLICATION_ID + "ACTION_CALCULATE";

  /**
   * Creates an IntentService.  Invoked by your subclass's constructor.
   *
   * @param name Used to name the worker thread, important only for debugging.
   */
  public Serv(String name) {
    super(Serv.class.getSimpleName());
  }

  @Override
  protected void onHandleIntent(@Nullable Intent intent) {

  }
}
