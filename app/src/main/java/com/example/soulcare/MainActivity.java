package com.example.soulcare;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int counter = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText User = findViewById(R.id.Username);
        final EditText Pass = findViewById(R.id.Password);
        Button Login = findViewById(R.id.login);
        Button Register = findViewById(R.id.register);
        TextView Attempts = findViewById(R.id.attempts);

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        TextView ForgotPass = findViewById(R.id.forgotPass);
        TextView ForgotUser = findViewById(R.id.forgotUser);
        SpannableString ss = new SpannableString("Forgot Password?");
        ClickableSpan CS = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast toast = Toast.makeText(context, "Forgot Password", duration);
                int forgotValue = 1;
                openForgotScreen(forgotValue);
            }
        };
        ClickableSpan CS2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast toast2 = Toast.makeText(context, "Forgot Username", duration);
                int forgotValue = 2;
                openForgotScreen(forgotValue);
            }
        };

        ss.setSpan(CS, 0, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForgotPass.setText(ss);
        ForgotPass.setMovementMethod(LinkMovementMethod.getInstance());
        ss.setSpan(CS2, 0, 14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ForgotUser.setText(ss);
        ForgotUser.setMovementMethod(LinkMovementMethod.getInstance());

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = User.getText().toString();
                String password = Pass.getText().toString();
                //correct login
                if(username.matches("abc") && password.matches("123")){
                    CharSequence text = "Valid Login";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    openHomeScreen();
                }

                else{
                    CharSequence text = "Invalid Login";

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    counter--;
                    Attempts.setText("Attempts remaining: " + String.valueOf(counter));
                    if(counter == 0){
                        Login.setEnabled(false);
                    }
                }
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });
    }


    public void openRegister(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
    public void openHomeScreen(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
    public void openForgotScreen(int forgotValue){

        Intent intent = new Intent(this, Forgot.class);
        intent.putExtra("Forgot", forgotValue);
        startActivity(intent);

    }
}