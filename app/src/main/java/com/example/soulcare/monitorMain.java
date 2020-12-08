package com.example.soulcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class monitorMain extends AppCompatActivity {
    private Database mydb;
    Button addmeds, listmeds, record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_main);


        addmeds = findViewById(R.id.Button8);
        listmeds = findViewById(R.id.Button7);
        record = findViewById(R.id.button_record);
        //mydb = new Database(this);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_r();
            }
        });




        addmeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_medicines();
            }
        });

        listmeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medList();
            }
        });

    }
    public void medList(){
        Intent in = new Intent(this, list_medicines.class);
        startActivity(in);
    }
    public void add_medicines(){
        Intent in = new Intent(this, add_meds.class);
        startActivity(in);

    }
    public void add_r(){
        Intent in = new Intent(this, add_record.class);
        startActivity(in);

    }}