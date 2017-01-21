package com.example.hp.exercise11_webservice_and_database;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button start, stop, getlastid;
    private int lastid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper2 mydb = new DBHelper2();
        SQLiteDatabase db = mydb.getWritableDatabase();

        start = (Button) findViewById(R.id.btnstart);
        stop = (Button) findViewById(R.id.btnstop);
        getlastid = (Button) findViewById(R.id.getlastid);
        getlastid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBController dbController = new DBController(MainActivity.this);
                lastid = dbController.gettopid();
                Toast.makeText(MainActivity.this, "topid = "+dbController.gettopid(), Toast.LENGTH_SHORT).show();

            }
        });
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!Helper.isMyServiceRunning(MainActivity.this, UpdateService.class)) {
                    startService(new Intent(MainActivity.this, UpdateService.class));
                    Toast.makeText(MainActivity.this, "Service Started", Toast.LENGTH_SHORT).show();
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                stopService(new Intent(MainActivity.this, UpdateService.class));
            }
        });
        Button ViewDB = (Button) findViewById(R.id.viewDB);
        ViewDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView List1 = (ListView) findViewById(R.id.list1);
                DBController dbController = new DBController(MainActivity.this);
                ArrayList<news> mynews = dbController.Select_from_DB();
                myadapter ad = new myadapter(mynews);
                List1.setAdapter(ad);
            }
        });



    }
    public class myadapter extends BaseAdapter
    {
        ArrayList<news> mynews;
        public myadapter(ArrayList inputnews)
        {
            mynews = inputnews;
        }

        @Override
        public int getCount() {
            return mynews.size();
        }

        @Override
        public Object getItem(int position) {
            return mynews.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View mylist = inflater.inflate(R.layout.list,parent,false);
            TextView txtservice = (TextView) mylist.findViewById(R.id.service);
            TextView txttitr = (TextView) mylist.findViewById(R.id.titr);
            TextView txtjdate = (TextView) mylist.findViewById(R.id.jdate);
            TextView txtlead = (TextView) mylist.findViewById(R.id.lead);
            news tmp_news = new news();
            tmp_news.setJdate(mynews.get(position).getJdate());
            tmp_news.setTitr(mynews.get(position).getTitr());
            tmp_news.setLead(mynews.get(position).getLead());
            tmp_news.setService(mynews.get(position).getService());

           txttitr.setText(tmp_news.getTitr());
            txtlead.setText(tmp_news.getLead());
            txtservice.setText(tmp_news.getService());
            txtjdate.setText(tmp_news.getJdate());

            return mylist;



        }
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
