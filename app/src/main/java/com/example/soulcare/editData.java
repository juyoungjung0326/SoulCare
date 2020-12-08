package com.example.soulcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class editData extends AppCompatActivity {
    private static final String TAG = "Edit Medicine Name";
    Button button1, button2, button3;
    EditText text;
    Database mydb;
    String selectname;
    int selectid;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        button1 = findViewById(R.id.del);
        button2 = findViewById(R.id.save);
        button3 = findViewById(R.id.timer);
        text = findViewById(R.id.name);
        mydb = new Database(this);

        Intent newInt = getIntent();
        selectid = newInt.getIntExtra("id", -1);
        selectname = newInt.getStringExtra("name");

        text.setText(selectname);
        image = findViewById(R.id.quess);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(editData.this, "EDIT / DELETE Medicine Name here", Toast.LENGTH_LONG).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = text.getText().toString();
                if(!item.equals("")){
                    mydb.updateName(item, selectid, selectname);
                    Toast.makeText(editData.this, "Medicine Name Updated!", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(editData.this, "You must enter a  medicine name", Toast.LENGTH_LONG).show();
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.deleteName(selectid, selectname);
                text.setText("");
                Toast.makeText(editData.this, "Removed the medicine!", Toast.LENGTH_LONG).show();

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(editData.this, setTimer.class);
                in.putExtra("phone", selectname);
                startActivity(in);
            }
        });

    }
}