package com.example.soulcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Contacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
    }

    public void launchCreateContact(View v)
    {
        Intent i = new Intent(this, create_contact.class);
        startActivity(i);
    }

    public void launchViewContacts(View v)
    {
        Intent i = new Intent(this, view_contacts.class);
        startActivity(i);
    }
    public void launchCallSMS(View v)
    {
        Intent i = new Intent(this,phone_sms.class);
        startActivity(i);
    }
}