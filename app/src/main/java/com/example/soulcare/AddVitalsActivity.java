package com.example.soulcare;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddVitalsActivity extends AppCompatActivity {

    Button vital_submit;
    EditText date_field, temp_field, pulse_field, bp_field, gl_field, cl_field;
    boolean formCheck = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vitals);

        vital_submit = findViewById(R.id.vital_submit);
        date_field = findViewById(R.id.date_field);
        temp_field = findViewById(R.id.temp_field);
        pulse_field = findViewById(R.id.pulse_field);
        bp_field = findViewById(R.id.bp_field);
        gl_field = findViewById(R.id.gl_field);
        cl_field = findViewById(R.id.cl_field);

        VitalSignsDatabase vitalSignDB = new VitalSignsDatabase(AddVitalsActivity.this);
    }

    public void submitForm(View v)
    {
        VitalSigns vitalSign = null;

        try {
            vitalSign = new VitalSigns(-1, date_field.getText().toString(), Integer.parseInt(temp_field.getText().toString()),
                    Integer.parseInt(pulse_field.getText().toString()), bp_field.getText().toString(), gl_field.getText().toString(), cl_field.getText().toString());
            VitalSignsDatabase vitalAdder = new VitalSignsDatabase(AddVitalsActivity.this);
            boolean success = vitalAdder.addForm(vitalSign);
            Toast.makeText(this,"Submitted!", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, ViewVitals.class);
            startActivity(i);

        } catch (Exception e) {

            Toast.makeText(this, "ERROR: Empty Fields", Toast.LENGTH_LONG).show();

        }




    }
    public void launchHelp(View v)
    {
        Intent i = new Intent(this, find_pulse.class);
        startActivity(i);
    }
}