package com.example.hp.exercise11_webservice_and_database;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        DBHelper mydb = new DBHelper();
        SQLiteDatabase db = mydb.getWritableDatabase();
        String createStudent = "insert into 'student' ('name' , 'family') values ('mahdi','heravi') ";
        db.execSQL(createStudent);
        db.close();
*/
        Intent myintent  = new Intent(MainActivity.this,UpdateService.class);
        startService(myintent);
    }

    public class DBHelper extends SQLiteOpenHelper {
        public DBHelper() {
            super(MainActivity.this, "myapp2.db", null, 2);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("========", "CREATED");
            String Createtbl = "CREATE TABLE 'student' ('id' INTEGER PRIMARY KEY  NOT NULL , 'name' VARCHAR, 'family' VARCHAR)";
            db.execSQL(Createtbl);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion == 1 && newVersion == 2) {
                Log.d("========", "ver2");
                String Createtbl = "CREATE TABLE 'teacher' ('id' INTEGER PRIMARY KEY  NOT NULL , 'name' VARCHAR, 'family' VARCHAR)";
                db.execSQL(Createtbl);

            }

        }
    }
}
