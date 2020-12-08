package com.example.soulcare;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class ViewVitals extends AppCompatActivity {

    ListView viewForms;
    VitalSignsDatabase viewList;
    ArrayAdapter formArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vitals);

        //assigning viewForm to the listView id
        viewForms = findViewById(R.id.viewForms);
        //creating a new instance of the database
        viewList = new VitalSignsDatabase(ViewVitals.this);

        //new instance of the adapter to get the data from the database
        formArrayAdapter = new ArrayAdapter<VitalSigns>(ViewVitals.this, R.layout.row2, viewList.getData());
        viewForms.setAdapter(formArrayAdapter);

        registerForContextMenu(viewForms);


    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId())
        {
            case R.id.delete_id:
                //casting viewForms to a VitalSigns class and getting the data from that position
                VitalSigns formChoice = (VitalSigns)viewForms.getItemAtPosition(info.position);
                //deleting the row
                viewList.deleteRow(formChoice);
                Toast.makeText(this,"Form Deleted",Toast.LENGTH_SHORT).show();
                //refreshing the screen
                formArrayAdapter = new ArrayAdapter<VitalSigns>(ViewVitals.this, R.layout.row2,viewList.getData());
                viewForms.setAdapter(formArrayAdapter);
        }
        return super.onContextItemSelected(item);
    }
}