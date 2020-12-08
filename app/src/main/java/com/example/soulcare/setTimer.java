package com.example.soulcare;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import java.util.Locale;

public class setTimer extends AppCompatActivity {
    TextView counter, timerImage, change;
    ImageView stop;

    private CountDownTimer timer ;
    Database mydb;
    int SEND_SM = 1;
    private long timeleft = 60000 * 60;
    //private long timeleft = 60000;
    //private long left = 60000;
    private long left = 60000 * 60;
    private long mEndTime;
    private boolean timeRUN = false;
    String selectname;
    String phone_num;

    String SENT = "SMS_SENT";
    String Delivered = "SMS_Delivered";
    PendingIntent sentPI, deliveredPI;
    BroadcastReceiver smsSentReceive, smsDelivered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_timer);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel ch = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager mg = getSystemService(NotificationManager.class);
            mg.createNotificationChannel(ch);
        }

        sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(Delivered), 0);


        if (checkpermisiion(Manifest.permission.SEND_SMS)){

        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SM);
        }

        ListView myList = (ListView) findViewById(R.id.list);

        counter = findViewById(R.id.count);
        timerImage = findViewById(R.id.time);
        stop = findViewById(R.id.stoptime);
        change = findViewById(R.id.start);
        mydb = new Database(this);
        Intent newInt = getIntent();
        selectname = newInt.getStringExtra("phone");
        Cursor data = mydb.getTime(selectname);
        data.moveToFirst();
        String c = data.getString(0);
        timeleft *= Long.parseLong(c);
        left = timeleft;

        Cursor data1 = mydb.getPhone(selectname);
        data1.moveToFirst();
        phone_num = data1.getString(0);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resettimer();
            }
        });

        timerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeRUN){
                    stoptimer();
                }else{
                    starttimer();
                }
            }
        });

    }
    public void resettimer(){
        timeleft = left;
        updateCountDown();
        if (timeRUN){stoptimer();}

    }

    @Override
    protected void onResume() {
        super.onResume();
        smsSentReceive = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch(getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(setTimer.this, "SMS Sent!", Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(setTimer.this, "Generic Failure!", Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(setTimer.this, "No Service!", Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(setTimer.this, "Null PDU!", Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(setTimer.this, "Radio OFF!", Toast.LENGTH_LONG).show();
                        break;

                }
            }
        };
        smsDelivered = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(setTimer.this, "SMS Delivered!", Toast.LENGTH_LONG).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(setTimer.this, "SMS not Delivered!", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };
        registerReceiver(smsSentReceive, new IntentFilter(SENT));
        registerReceiver(smsDelivered, new IntentFilter(Delivered));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(smsDelivered);
        unregisterReceiver(smsSentReceive);
    }

    public void starttimer() {
        mEndTime = System.currentTimeMillis() + timeleft;
        timer = new CountDownTimer(timeleft, 1000) {
            @Override
            public void onTick(long l) {
                timeleft = l;
                updateCountDown();
            }

            @Override
            public void onFinish() {
                timeRUN = false;
                updateTimer();
            }
        }.start();

        timeRUN = true;
        updateTimer();
    }
    public void stoptimer(){
        timer.cancel();
        timeRUN = false;
        updateTimer();
    }

    public  void updateCountDown(){
        //int hours = (int) timeleft/ 60000;
        //int minutes = (int) (timeleft % 60000) / 1000;
        int hours = (int) (timeleft/(1000)) / 3600;
        int minutes = (int) ((timeleft /1000) % 3600) / 60;

        String timeleftFormat = String.format(Locale.getDefault(), "%02d:%02d", hours, minutes);
        counter.setText(timeleftFormat);
    }
    public void updateTimer(){
        if (timeRUN){
            change.setText("PAUSE");
        }else{
            change.setText("START");
        if (timeleft < 1000)
        {
            String sa = null;
            Toast.makeText(setTimer.this, "Timer Stopped, Sending Text!", Toast.LENGTH_LONG).show();
            //sendSms(phone_num, selectname);
            if (checkpermisiion(Manifest.permission.SEND_SMS)) {
                SmsManager myman = SmsManager.getDefault();
                sa = "It is now time to take your medicine. Please take your medicine '" + selectname + "' in time. Thank you.";
                myman.sendTextMessage(phone_num, null, sa, sentPI, deliveredPI);
            }else{
                Toast.makeText(setTimer.this, "Permission Denied", Toast.LENGTH_LONG).show();
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(setTimer.this, "My Notification");
            builder.setContentTitle("Important Info on your Medicine");
            builder.setContentText(sa);
            builder.setSmallIcon(R.drawable.ic_baseline_medical_services_24);
            builder.setAutoCancel(true);
            NotificationManagerCompat mg = NotificationManagerCompat.from(setTimer.this);
            mg.notify(1, builder.build());
            timeleft = left;
            //updateCountDown();
        }}

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("millisleft", timeleft);
        editor.putBoolean("timeRunning", timeRUN);
        editor.putLong("endTime", mEndTime);
        editor.apply();
        if (timer != null){
            timer.cancel();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        timeleft = prefs.getLong("millisleft", timeleft);
        timeRUN = prefs.getBoolean("timeRunning", false);
        updateTimer();
        updateCountDown();
        //updateTimer();
        if (timeRUN){
            mEndTime = prefs.getLong("endTime", 0);
            timeleft = mEndTime - System.currentTimeMillis();
            if (timeleft < 0) {
                timeleft = left;
                timeRUN = false;
                updateCountDown();
                updateTimer();
            }else{
                starttimer();
            }
        }

    }

    private boolean checkpermisiion(String p){
        int pt = ContextCompat.checkSelfPermission(this, p);
        return pt == PackageManager.PERMISSION_GRANTED;
    }



    }
