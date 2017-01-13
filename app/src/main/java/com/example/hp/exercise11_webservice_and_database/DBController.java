package com.example.hp.exercise11_webservice_and_database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hp on 01/13/2017.
 */

public class DBController {

    SQLiteDatabase db;


    public DBController(Context context) {
        DBHelper mydb = new DBHelper(context);
        db = mydb.getWritableDatabase();

    }
    public int gettopid()
    {
        int id=0;

        String command ="SELECT id FROM news order by id desc limit 1";
        Cursor tmpnews = db.query("news",new String[]{"id"},null,null,null,null,"id","1");
        id = tmpnews.getInt(tmpnews.getColumnIndex("id"));
        return id;
    }
    public void insert(news mynews)
    {
        String tmp;

        tmp = mynews.getTitr();
        tmp.replace('\'',' ');
        tmp.replace('\"',' ');
        mynews.setTitr(tmp);

        tmp = mynews.getLead();
        tmp.replace('\'',' ');
        tmp.replace('\"',' ');
        mynews.setLead(tmp);

        String command = "insert into 'news' ('id' , 'service' , 'titr' , 'lead' , 'jdate') values ('"+mynews.getId()+"','"+mynews.getService()+"', '"+mynews.getTitr()+"','"+mynews.getLead()+"','"+mynews.getJdate()+"') ";
        db.execSQL(command);

    }
    public void insert(news[] mynews)
    {
        for(news item : mynews)
        {
            insert(item);
        }
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
