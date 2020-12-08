package com.example.soulcare;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class create_contact extends AppCompatActivity {

    Button create_button;
    EditText contact_name, contact_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        create_button = findViewById(R.id.create_button);
        contact_name = findViewById(R.id.contact_name);
        contact_number = findViewById(R.id.contact_number);

        ContactDatabase contactDB = new ContactDatabase(create_contact.this);

    }

    public void addContact(View v)
    {
        Contact_Class newContact;
        try{
            newContact = new Contact_Class(-1,contact_name.getText().toString(),contact_number.getText().toString());
            ContactDatabase contactAdd = new ContactDatabase(create_contact.this);
            boolean success = contactAdd.insertContact(newContact);
            Toast.makeText(this,"Contact Created!",Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error: Empty Fields",Toast.LENGTH_SHORT);
        }
    }
}