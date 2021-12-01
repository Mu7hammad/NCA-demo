package com.example.nca_demo.Screens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.nca_demo.MainActivity;
import com.example.nca_demo.R;
import com.example.nca_demo.Others.StaticVar;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler();
    }

    private void handler() {
        new Handler().postDelayed(() -> {
            if (is_login()) {
                /* have account, lets start */
                startActivity(new Intent(Splash.this, MainActivity.class));
            } else {
                /* not have account, login first */
                startActivity(new Intent(Splash.this, Login.class));
            }
        }, 1000);
    }

    private boolean is_login() {
        /* user is login */
        if (getSharedPreferences("UserData", Context.MODE_PRIVATE).getBoolean("login", false)){
            /* lest's get user code */
            StaticVar.student.setCode(getSharedPreferences("UserData", Context.MODE_PRIVATE).getString("code", ""));
            return true;
        } else {
            return false;
        }
    }
}