package com.example.soulcare;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Adapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class view_contacts extends AppCompatActivity {

    ListView contact_list;
    ContactDatabase viewList;
    ArrayAdapter contactArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);

        contact_list = findViewById(R.id.contact_list);
        viewList = new ContactDatabase(view_contacts.this);

        contactArrayAdapter = new ArrayAdapter<Contact_Class>(view_contacts.this, R.layout.row,viewList.getContacts());
        contact_list.setAdapter(contactArrayAdapter);

        registerForContextMenu(contact_list);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_contact_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
        switch(item.getItemId())
        {
            case R.id.call_contact:
                //String contact_number = null;
                Contact_Class contactChoice = (Contact_Class)contact_list.getItemAtPosition(info.position);
                Intent i = new Intent(this,phone_sms.class);
                i.putExtra("contact_number",contactChoice.getContact_number());
                startActivity(i);
                break;
            case R.id.delete_id:
                Contact_Class deleteContact = (Contact_Class)contact_list.getItemAtPosition(info.position);
                viewList.deleteRow(deleteContact);
                contactArrayAdapter = new ArrayAdapter<Contact_Class>(view_contacts.this,R.layout.row,viewList.getContacts());
                contact_list.setAdapter(contactArrayAdapter);
                break;
        }
        return super.onContextItemSelected(item);
    }
}