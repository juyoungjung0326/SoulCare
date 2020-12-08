package com.example.soulcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VitalsMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals_menu);
    }

    public void launchAddVitals(View v)
    {
        Intent i = new Intent(this, AddVitalsActivity.class);
        startActivity(i);
    }

    public void launchViewList(View v)
    {
        Intent i = new Intent(this, ViewVitals.class);
        startActivity(i);
    }
}