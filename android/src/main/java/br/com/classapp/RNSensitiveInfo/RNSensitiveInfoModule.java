package br.com.classapp.RNSensitiveInfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.facebook.react.bridge.*;

import java.util.Map;

public class RNSensitiveInfoModule extends ReactContextBaseJavaModule {

  private static final String DEFAULT_SHARED_PREFS_NAME = "app";
  private SharedPreferences mSharedPreferences;

  public RNSensitiveInfoModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "RNSensitiveInfo";
  }

  @ReactMethod
  public void getItem(String key, ReadableMap options, Promise pm) {

    String name = sharedPreferencesName(options);

    String value = sharedPreferences(name).getString(key, null);
    pm.resolve(value);
  }

  @ReactMethod
  public void setItem(String key, String value, ReadableMap options) {

    String name = sharedPreferencesName(options);

    putExtra(key, value, sharedPreferences(name));
  }

  @ReactMethod
  public void deleteItem(String key, ReadableMap options) {

    String name = sharedPreferencesName(options);

    SharedPreferences mSharedPreferences = sharedPreferences(name);

    SharedPreferences.Editor editor = mSharedPreferences.edit();

    editor.remove(key).apply();
  }

  @ReactMethod
  public void getAllItems(ReadableMap options, Promise pm) {

    String name = sharedPreferencesName(options);

    Map<String, ?> allEntries = sharedPreferences(name).getAll();
    WritableMap resultData = new WritableNativeMap();

    for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
      resultData.putString(entry.getKey(), entry.getValue().toString());
    }
    pm.resolve(resultData);
  }

  private void putExtra(String key, Object value, SharedPreferences mSharedPreferences) {
    SharedPreferences.Editor editor = mSharedPreferences.edit();
    if (value instanceof String) {
      editor.putString(key, (String) value).apply();
    } else if (value instanceof Boolean) {
      editor.putBoolean(key, (Boolean) value).apply();
    } else if (value instanceof Integer) {
      editor.putInt(key, (Integer) value).apply();
    } else if (value instanceof Long) {
      editor.putLong(key, (Long) value).apply();
    } else if (value instanceof Float) {
      editor.putFloat(key, (Float) value).apply();
    }
  }

  private SharedPreferences sharedPreferences(String name) {
    if(mSharedPreferences == null) {
      mSharedPreferences = getReactApplicationContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    return mSharedPreferences;
  }

  @NonNull
  private String sharedPreferencesName(ReadableMap options) {
    String name = options.hasKey("sharedPreferencesName") ? options.getString("sharedPreferencesName") : DEFAULT_SHARED_PREFS_NAME;
    if (name == null) {
      name = DEFAULT_SHARED_PREFS_NAME;
    }
    return name;
  }
}
