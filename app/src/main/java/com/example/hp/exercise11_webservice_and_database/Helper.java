package com.example.hp.exercise11_webservice_and_database;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by hp on 01/13/2017.
 */

public class Helper {
    public static boolean isMyServiceRunning(Context context , Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
