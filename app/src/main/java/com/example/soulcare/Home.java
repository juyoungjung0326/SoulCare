package com.example.soulcare;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button Info = findViewById(R.id.Info);
        Button Vital = findViewById(R.id.Vital);
        Button Medication = findViewById(R.id.Medication);
        Button Search = findViewById(R.id.Search);
        Button Monitor = findViewById(R.id.Monitor);
        Button Communication = findViewById(R.id.Communication);
        Button Logout = findViewById(R.id.Logout);
        //Button DietManagement = findViewById(R.id.Diet);


        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        Vital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, VitalsMenu.class);
                startActivity(i);

            }
        });
        Medication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, monitorMain.class);
                startActivity(i);

            }
        });
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, MainSearch.class);
                startActivity(i);

            }
        });
        Monitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, monitorMain.class);
                startActivity(i);

            }
        });
        Communication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Contacts.class);
                startActivity(i);

            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, MainActivity.class);
                startActivity(i);

            }
        });

    }
    public void launchDiet(View v) {
        Intent i = new Intent(this, dietActivity.class);
        startActivity(i);

    }


}