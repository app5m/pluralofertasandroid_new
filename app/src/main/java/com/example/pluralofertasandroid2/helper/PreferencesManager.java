package com.example.pluralofertasandroid2.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    public static final String ENTERING_FIRST_TIME = "EnteringFirstTime";

    public static void storeInt(Context context, String key, int value) {
        SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInt(Context context, String key, int defaultValue){
        return context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).getInt(key, defaultValue);
    }
}
