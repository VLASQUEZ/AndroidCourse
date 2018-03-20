package com.olimpiait.reconoser.sharedpreferences.app;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by AndresVelasquez on 14/03/18.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(3000);
    }
}
