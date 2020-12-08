package com.example.soulcare;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class phone_sms extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    private EditText mEditTextNumber;
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    EditText composed_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_sms);

        mEditTextNumber = findViewById(R.id.phone_number);
        composed_message = findViewById(R.id.composed_message);
        Intent i = getIntent();
        String intent_number = i.getStringExtra("contact_number");


        if(intent_number != null)
        {
            mEditTextNumber.setText(intent_number, TextView.BufferType.EDITABLE);
        }
        ImageView imageCall = findViewById(R.id.image_call);
        ImageView sms_send = findViewById(R.id.sms_send);

        if(checkPermissions(Manifest.permission.SEND_SMS))
        {
            //does nothing. Makes sure we have permission to send sms
        }
        else
        {
            //requests to have access to sending sms
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},SEND_SMS_PERMISSION_REQUEST_CODE);
        }


    }

    public void sendMessage(View v)
    {
        String number = mEditTextNumber.getText().toString();
        String message = composed_message.getText().toString();
        if(number.trim().length() <= 0 || message.trim().length() <= 0)
        {
            Toast.makeText(this,"Make Sure All Fields are Filled",Toast.LENGTH_SHORT).show();
            return;
        }

        if(checkPermissions(Manifest.permission.SEND_SMS))
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number,null,message,null,null);
            Toast.makeText(this,"Message Sent!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkPermissions(String permission)
    {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
    public void newCall(View v)
    {
        makePhoneCall();
    }

    private void makePhoneCall()
    {
        String number = mEditTextNumber.getText().toString();
        if(number.trim().length() > 0)
        {
            if(ContextCompat.checkSelfPermission(phone_sms.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(phone_sms.this, new String[]
                        {Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }
            else
            {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
        else
        {
            Toast.makeText(phone_sms.this,"Enter a Phone Number", Toast.LENGTH_SHORT ).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                makePhoneCall();
            }
            else
            {
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }
}