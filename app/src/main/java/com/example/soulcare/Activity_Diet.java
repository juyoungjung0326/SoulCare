package com.example.soulcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

public class Activity_Diet extends AppCompatActivity {
//    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button diet, med;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

//        toolbar = findViewById(R.id.myToolBar);
//        setSupportActionBar(toolbar);


        diet = findViewById(R.id.dietBtn);
        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Diet.this, dietActivity.class));
            }
        });
    }
}