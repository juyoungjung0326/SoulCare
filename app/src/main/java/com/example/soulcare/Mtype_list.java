package com.example.soulcare;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Mtype_list extends AppCompatActivity {
    Database mi;
    ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtype_list);
        mi = new Database(this);
        l = findViewById(R.id.mtype);
        Cursor data = mi.getRecord();
        l.setDividerHeight(20);
        ArrayList<String> listData1 = new ArrayList<>();
        int i = 1;
        while (data.moveToNext()) {
            listData1.add( Integer.toString(i) + ".  " +data.getString(1));
            i++;
            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData1);
            l.setAdapter(adapter);
        }
        data.moveToFirst();
    }
}