package com.example.soulcare;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class add_edit_doctor extends AppCompatActivity {
    Database_doctor myDoc;
    ListView list1;
    Dialog dialog;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_doctor);

        text = findViewById(R.id.text);
        list1 = findViewById(R.id.doc_list);
        list1.setDividerHeight(20);
        myDoc = new Database_doctor(this);
        Cursor data = myDoc.getList();

        ArrayList<String> listData1 = new ArrayList<>();

        while (data.moveToNext()) {
            listData1.add(data.getString(1) + "\n" + data.getString(3) + "\n" +
                    data.getString(4) + "," + data.getString(6) + " " +
                    data.getString(5) + "\n" + data.getString(2));
            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData1);
            list1.setAdapter(adapter);
        }
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                dialog = new Dialog(add_edit_doctor.this);
                dialog.setContentView(R.layout.dialog);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

                }
                dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //dialog.setCancelable(false);

                Button yes = dialog.findViewById(R.id.btn_yes);
                Button no = dialog.findViewById(R.id.btn_no);
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(add_edit_doctor.this, "Doctor added!", Toast.LENGTH_LONG).show();
                        Intent in = new Intent();
                        in.putExtra("any",name);
                        setResult(RESULT_OK, in);
                        finish();
                    }
                });
                dialog.show();
            }
        });





    }
}