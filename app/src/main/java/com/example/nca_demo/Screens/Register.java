package com.example.nca_demo.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.nca_demo.R;

public class Register extends AppCompatActivity {

    private TextView tv_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tv_to_login = findViewById(R.id.to_login);
        tv_to_login.setPaintFlags(tv_to_login.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

    }
}