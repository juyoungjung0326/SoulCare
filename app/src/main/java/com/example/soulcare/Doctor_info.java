package com.example.soulcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Doctor_info extends AppCompatActivity {
    TextView text;
    private String result;
    Button add_edit;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private String text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);
        text = findViewById(R.id.text);
        add_edit = findViewById(R.id.button_add_edit);
        add_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Doctor_info.this, add_edit_doctor.class);
                startActivityForResult(in, 1);
            }
        });
        loadData();
        updateViews();


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                result = data.getStringExtra("any");
                text.setText(result);
                saveData();
            }
            if (resultCode == RESULT_CANCELED) {
                text.setText("Nothing selected");
            }
        }
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT, result);
        editor.apply();
    }
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text1 = sharedPreferences.getString(TEXT, "");
    }
    public void updateViews() {
        text.setText(text1);
    }
}



