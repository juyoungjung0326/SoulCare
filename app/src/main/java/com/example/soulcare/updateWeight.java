package com.example.soulcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class updateWeight extends AppCompatActivity {
    public static final String NEW_WEIGHT = "com.example.diet.NEW_WEIGHT;";

    Button updateWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_weight);

        updateWeight = (Button) findViewById(R.id.submitButton);
        updateWeight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText editWeight = (EditText) findViewById(R.id.fillWeight);
                int num = Integer.valueOf(editWeight.getText().toString());

                if (num == 0) {
                    Toast.makeText (v.getContext(), "Invalid Weight", Toast.LENGTH_SHORT).show();
                }

                else{
                    Intent intent = new Intent(updateWeight.this, dietActivity.class);
                    intent.putExtra(NEW_WEIGHT, num);
                    startActivity(intent);
                }

            }
        });
    }
}