package com.example.soulcare;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class addDin extends AppCompatActivity {

    DatabaseHelper3 dinDB;

    Button submitBtn, viewBtn, updateBtn, deleteBtn;
    EditText foodInput, servingInput, calorieInput, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_din);

        dinDB = new DatabaseHelper3(this);

        foodInput = (EditText) findViewById(R.id.fillFood1);
        servingInput = (EditText) findViewById(R.id.fillServing);
        calorieInput = (EditText) findViewById(R.id.fillCal);
        id = (EditText) findViewById(R.id.fillEntry);

        submitBtn = (Button) findViewById(R.id.submitButton);
        viewBtn = (Button) findViewById(R.id.viewButton);
        updateBtn = (Button) findViewById(R.id.updateButton);
        deleteBtn = (Button) findViewById(R.id.deleteButton);

        AddData();
        ViewData();
        UpdateData();
        DeleteData();
    }

    public void AddData() {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodName = foodInput.getText().toString();
                String serving = servingInput.getText().toString();
                String calories = calorieInput.getText().toString();

                boolean insertData = dinDB.addData(foodName, serving, calories);

                if(insertData == true) {
                    Toast.makeText(addDin.this, "Data successfully inserted!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(addDin.this, dietActivity.class)); //takes back to main
                } else {
                    Toast.makeText(addDin.this, "Something went wrong.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void ViewData() {
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor data = dinDB.showData();

                if (data.getCount() == 0) {
                    display("Error", "No Data Found.");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (data.moveToNext()) {
                    buffer.append("Entry " + data.getString(0) + "\n");
                    buffer.append("Food: " + data.getString(1) + "\n");
                    buffer.append("Serving: " + data.getString(2) + "\n");
                    buffer.append("Calories: " + data.getString(3) + "\n\n");

                    display("All Stored Data:", buffer.toString());
                }
            }
        });
    }

    public void display(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void UpdateData(){
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = id.getText().toString().length();
                if (temp > 0) {
                    boolean update = dinDB.updateData(id.getText().toString(), foodInput.getText().toString(),
                            servingInput.getText().toString(), calorieInput.getText().toString());
                    if (update == true) {
                        Toast.makeText(addDin.this, "Successfully Updated Data!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(addDin.this, "Something Went Wrong.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(addDin.this, "You Must Enter An ID to Update.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void DeleteData(){
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = id.getText().toString().length();
                if(temp > 0){
                    Integer deleteRow = dinDB.deleteData(id.getText().toString());
                    if(deleteRow > 0){
                        Toast.makeText(addDin.this, "Successfully Deleted The Data!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(addDin.this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(addDin.this, "You Must Enter An ID to Delete :(.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}