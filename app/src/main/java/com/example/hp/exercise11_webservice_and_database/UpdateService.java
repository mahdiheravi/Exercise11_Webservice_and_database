package com.example.hp.exercise11_webservice_and_database;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class UpdateService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        EventBus.getDefault().post(new MessageEvent("Service Started" , 1));
        StringRequest request = new StringRequest(Request.Method.GET, DB.API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        EventBus.getDefault().post(new MessageEvent("Response Received" , 2));
                        Gson gson = new Gson();
                        news[] mynews =  gson.fromJson(response , news[].class);
                        DBController dbController = new DBController(UpdateService.this);

                        dbController.insert(mynews);
                        EventBus.getDefault().post(new MessageEvent("DB Task Finished" , 3));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(UpdateService.this).add(request);
        return START_STICKY;






    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }



}
