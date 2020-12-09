package com.example.soulcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class dietActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button brek, lun, din, snak, weight, toMain;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);


        brek = findViewById(R.id.addbrk);
        brek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dietActivity.this, addBrek.class));
            }
        });

        lun = findViewById(R.id.addlun);
        lun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dietActivity.this, addLun.class));
            }
        });

        din = findViewById(R.id.adddin);
        din.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dietActivity.this, addDin.class));
            }
        });

        snak = findViewById(R.id.addsnack);
        snak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dietActivity.this, addSnack.class));
            }
        });

        weight = findViewById(R.id.weight);
        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dietActivity.this, updateWeight.class));
            }
        });

        toMain = (Button) findViewById(R.id.toMainBtn);
        toMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(dietActivity.this, MainActivity.class));            }
        });

        Intent intent = getIntent();
        int num = intent.getIntExtra(com.example.soulcare.updateWeight.NEW_WEIGHT, 0);
        TextView newWeight = (TextView) findViewById(R.id.currWeight);
        newWeight.setText("" + num);
    }
}