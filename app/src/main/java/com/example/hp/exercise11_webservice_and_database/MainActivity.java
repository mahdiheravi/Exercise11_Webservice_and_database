package com.example.hp.exercise11_webservice_and_database;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper2 mydb = new DBHelper2();
        SQLiteDatabase db = mydb.getWritableDatabase();

        start = (Button) findViewById(R.id.btnstart);
        stop = (Button) findViewById(R.id.btnstop);

        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                startService(new Intent(MainActivity.this, UpdateService.class));
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                stopService(new Intent(MainActivity.this, UpdateService.class));
            }
        });



    }

    public class DBHelper2 extends SQLiteOpenHelper {
        public DBHelper2() {
            super(MainActivity.this, "myapp2.db", null, 1);
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
