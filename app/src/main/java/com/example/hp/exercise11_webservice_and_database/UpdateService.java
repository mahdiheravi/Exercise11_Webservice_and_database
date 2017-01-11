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
    ArrayList<news> mynews;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Started", "STARTED");
        DBHelper mydb = new DBHelper();
        final SQLiteDatabase db = mydb.getWritableDatabase();
       // String url = "http://razmkhah.ir/b/index.php";
        String url = "http://www.ion.ir/news/GetJson";
        final StringRequest request = new StringRequest(Method.GET, url, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE", response);
                Gson mygson = new Gson();
                String createStudent;
                String tmptitr;

                mynews = mygson.fromJson(response, new TypeToken<List<news>>(){}.getType());
                Log.d("RESPONSE", "size="+mynews.size());

                for (int i=0;i<50;i++)
                {
                    Log.d("LOOP","i  = "+i);
                    Log.d("service",mynews.get(i).getService());
                    Log.d("titr",mynews.get(i).getTitr());
                    Log.d("lead",mynews.get(i).getLead());

                    tmptitr = mynews.get(i).getTitr();
                    tmptitr.replace('\'',' ');
                    mynews.get(i).setTitr(tmptitr);

                    tmptitr = mynews.get(i).getLead();
                    tmptitr.replace('\'',' ');
                    mynews.get(i).setLead(tmptitr);


                    createStudent = "insert into 'news' ('id' , 'service' , 'titr' , 'lead' , 'jdate') values ('"+mynews.get(i).getId()+"','"+mynews.get(i).getService()+"', '"+mynews.get(i).getTitr()+"','"+mynews.get(i).getLead()+"','"+mynews.get(i).getJdate()+"') ";
                     db.execSQL(createStudent);
                }
                db.close();
                Log.d("RESPONSE", "size="+mynews.size());
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

    public class DBHelper extends SQLiteOpenHelper {
        public DBHelper() {
            super(UpdateService.this, "myapp2.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String Createtbl = "CREATE TABLE 'news' ('id' INTEGER PRIMARY KEY  NOT NULL , 'service' VARCHAR, 'titr' VARCHAR, 'lead' VARCHAR, 'jdate' VARCHAR)";
            Log.d("========", "CREATED");
            db.execSQL(Createtbl);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
