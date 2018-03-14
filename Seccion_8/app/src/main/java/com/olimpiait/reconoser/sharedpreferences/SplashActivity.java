package com.olimpiait.reconoser.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.olimpiait.reconoser.sharedpreferences.Preferences.Preferences;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("loginPrefs",MODE_PRIVATE);

        Intent i = new Intent(this,LoginActivity.class);
        Intent iMain = new Intent(this,MainActivity.class);

        if(!TextUtils.isEmpty(Preferences.getUserEmailPrefs(prefs)) &&
                !TextUtils.isEmpty(Preferences.getUserPasswordPrefs(prefs))){
            startActivity(iMain);
        }
        else {
            startActivity(i);
        }
        finish();
    }

}
