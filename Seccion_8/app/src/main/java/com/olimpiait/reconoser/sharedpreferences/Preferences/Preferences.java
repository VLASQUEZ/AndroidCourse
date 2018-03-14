package com.olimpiait.reconoser.sharedpreferences.Preferences;

import android.content.SharedPreferences;

/**
 * Created by AndresVelasquez on 14/03/18.
 */

public class Preferences {

    public static String getUserEmailPrefs(SharedPreferences preferences){
        return preferences.getString("email",null);
    }
    public static String getUserPasswordPrefs(SharedPreferences preferences){
        return preferences.getString("password",null);
    }

}
