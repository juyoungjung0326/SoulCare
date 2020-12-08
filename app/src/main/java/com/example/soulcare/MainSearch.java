package com.example.soulcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainSearch extends AppCompatActivity {
    String result;
    Button Docinfo, Mtype, Hrecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);


        Docinfo = findViewById(R.id.Button1);
        Mtype = findViewById(R.id.Button2);
        Hrecord = findViewById(R.id.Button3);

        Hrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainSearch.this, healthrecord.class);
                startActivity(in);

            }
        });
        Docinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainSearch.this, Doctor_info.class);
                in.putExtra("bb",result);
                startActivityForResult(in, 1);
            }
        });
        Mtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainSearch.this,Mtype_list.class);
                startActivity(in);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                result = data.getStringExtra("any");

            }
        }
}}

