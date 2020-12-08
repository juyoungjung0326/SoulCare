package com.example.soulcare;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class add_meds extends AppCompatActivity {
    Database mydb;
    EditText medicine, phone, time;
    Button add_button;
    ImageView image1, image2;
    //ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meds);
        mydb = new Database(this);
        medicine = findViewById(R.id.medicine);
        phone = findViewById(R.id.phone);
        time = findViewById(R.id.time);
        add_button = findViewById(R.id.button_add);
        //add_view = findViewById(R.id.button_view);


        medicine.addTextChangedListener(text_watch);
        phone.addTextChangedListener(text_watch);
        time.addTextChangedListener(text_watch);
        image1 = findViewById(R.id.ques);
        image2 = findViewById(R.id.ques1);


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(add_meds.this, "Next of kin Phone Number", Toast.LENGTH_SHORT).show();
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(add_meds.this, "Please add Time in Hours", Toast.LENGTH_SHORT).show();
            }
        });


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String med = medicine.getText().toString();
                String ph = phone.getText().toString();
                String tm = time.getText().toString();
                if (phone.length() == 10 && time.length() != 0) {
                    addData(med, ph, tm);
                    medicine.setText("");
                    phone.setText("");
                    time.setText("");
                }else{
                    if ( phone.length() < 10)
                        phone.setError("Enter valid Phone Number");
                    if(time.length() == 0)
                        time.setError("Enter valid number");
                }

            }
        });
    }


    private TextWatcher text_watch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String changeMed = medicine.getText().toString().trim();
            String changePhone = medicine.getText().toString().trim();
            String changeTime = medicine.getText().toString().trim();
            add_button.setEnabled(!changeTime.isEmpty() && !changeMed.isEmpty() && !changePhone.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void addData(String med, String ph, String tm) {
        boolean isInserted = mydb.insertData(med, ph, tm);
        if (isInserted == true)
            Toast.makeText(add_meds.this, "Medicine added", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(add_meds.this, "Please Try Again!!", Toast.LENGTH_LONG).show();
    }
}
