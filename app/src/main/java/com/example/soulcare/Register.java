package com.example.soulcare;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText first = findViewById(R.id.First);
        final EditText last = findViewById(R.id.Last);
        EditText email = findViewById(R.id.Email);

    }
}