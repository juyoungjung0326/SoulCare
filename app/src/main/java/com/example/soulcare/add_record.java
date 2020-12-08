package com.example.soulcare;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class add_record extends AppCompatActivity {
    EditText a,b,c;
    Button add1;
    Database my;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        my = new Database(this);
        a = findViewById(R.id.type);
        b = findViewById(R.id.visit);
        c = findViewById(R.id.service);
        add1 = findViewById(R.id.add12);


        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String med = a.getText().toString();
                String ph = b.getText().toString();
                String tm = c.getText().toString();
                add_rec(med, ph, tm);
                a.setText("");
                b.setText("");
                c.setText("");

            }
        });
    }
    public void add_rec(String med, String ph, String tm) {
        boolean isInserted = my.insertRecord(med, ph, tm);
        if (isInserted == true)
            Toast.makeText(add_record.this, "Record Added", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(add_record.this, "Please Try Again!!", Toast.LENGTH_LONG).show();
    }
}