package com.example.hp.exercise11_webservice_and_database;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.greenrobot.eventbus.EventBus;

public class UpdateService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Started", "STARTED");
        String url = "http://razmkhah.ir/b/index.php";
       // String url = "http://www.ion.ir/news/GetJson";
        StringRequest request = new StringRequest(Method.GET, url, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE", response);
                EventBus.getDefault().post(new IntentServiceResults(Activity.RESULT_OK, "done!!"));


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
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
