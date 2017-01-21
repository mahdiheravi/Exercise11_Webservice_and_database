package com.example.hp.exercise11_webservice_and_database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by hp on 01/13/2017.
 */

public class DBController {

    SQLiteDatabase db;
    int count;


    public DBController(Context context) {
        DBHelper mydb = new DBHelper(context);
        count = 0;
        db = mydb.getWritableDatabase();

    }

    public int gettopid() {
        Cursor cursor = null;
        int id = 0;
        String command = "SELECT id FROM news order by id desc limit 1";
        Log.d("Mahdi", "rawQueryStart");
        cursor = db.rawQuery(command, null);
        Log.d("Mahdi", "rawQueryEnd");


        if (cursor.getCount() > 0) {

            cursor.moveToFirst();
            id = Integer.parseInt(cursor.getString(0));
//            id = cursor.getInt(cursor.getColumnIndex("id"));
        }


        cursor.close();
        return id;


    }

    public void insert(news mynews) {
        String tmp;

        tmp = mynews.getTitr();
        tmp.replace('\'', ' ');
        tmp.replace('\"', ' ');
        mynews.setTitr(tmp);

        tmp = mynews.getLead();
        tmp.replace('\'', ' ');
        tmp.replace('\"', ' ');
        mynews.setLead(tmp);
        Log.d("mahdi", "id = " + mynews.getId());
        String command = "insert into 'news' ('id' , 'service' , 'titr' , 'lead' , 'jdate') values ('" + mynews.getId() + "','" + mynews.getService() + "', '" + mynews.getTitr() + "','" + mynews.getLead() + "','" + mynews.getJdate() + "') ";
        db.execSQL(command);

    }

    public ArrayList<news> Select_from_DB() {
        ArrayList<news> mynews = new ArrayList<>();
        Cursor cursor = null;
        int id = 0;
        String command = "SELECT service,titr,lead,jdate FROM news ";
        Log.d("Mahdi", "rawQueryStart");
        cursor = db.rawQuery(command, null);
        Log.d("Mahdi", "rawQueryEnd");


        if (cursor.getCount() > 0) {
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        news tmp_news = new news();
                        tmp_news.setService(cursor.getString(cursor.getColumnIndex("service")));
                        tmp_news.setTitr(cursor.getString(cursor.getColumnIndex("titr")));
                        tmp_news.setLead(cursor.getString(cursor.getColumnIndex("lead")));
                        tmp_news.setJdate(cursor.getString(cursor.getColumnIndex("jdate")));


                        mynews.add(tmp_news);
                    } while (cursor.moveToNext());
                }
            }
        }

        cursor.close();

        return mynews;
    }


    public int insert(news[] mynews, int lastid) {
        for (news item : mynews) {
            if (item.getId() > lastid) {
                insert(item);
                count++;
            }

        }
        return count;
    }


    public class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, DB.DB_NAME, null, DB.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String Createtbl = "CREATE TABLE 'news' ('id' INTEGER PRIMARY KEY  NOT NULL , 'service' VARCHAR, 'titr' VARCHAR, 'lead' VARCHAR, 'jdate' VARCHAR)";
            Log.d("mahdi", "CREATED");
            db.execSQL(Createtbl);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
