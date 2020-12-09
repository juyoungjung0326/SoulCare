package com.example.soulcare;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class list_medicines extends AppCompatActivity {
    private static final String TAG = "Medicines List";
    Database mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_medicines);
        ListView myList = (ListView) findViewById(R.id.list);
        mydb = new Database(this);
        myList.setBackgroundColor(Color.parseColor("#D3D3D3"));
        myList.setDividerHeight(20);

        Cursor data = mydb.getList();
        ArrayList<String> listData = new ArrayList<>();
        if(data.getCount() == 0) {
            Toast.makeText(this, "Medicine List is Empty, ADD Medicine!!", Toast.LENGTH_SHORT).show();
        }else {
            while (data.moveToNext()) {
                listData.add(data.getString(1));
                ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
                myList.setAdapter(adapter);
            }}


        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemClick: You clicked on " + name);
                Cursor data = mydb.getID(name); // get id associated with that name
                int itemID = -1;
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if (itemID > -1) {
                    Log.d(TAG, "onItemClick: The ID is " + itemID);
                    Intent inad = new Intent(list_medicines.this, editData.class);
                    inad.putExtra("id", itemID);
                    inad.putExtra("name", name);
                    startActivity(inad);
                } else {
                    //mydb.deleteName();
                    Toast.makeText(list_medicines.this, "No ID associated with that medicine name", Toast.LENGTH_LONG).show();
                }

            }
        });



    }
    public String getNaam(String name){

        return name;
    }


}