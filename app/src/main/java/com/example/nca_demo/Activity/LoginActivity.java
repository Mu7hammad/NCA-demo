package com.example.nca_demo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.nca_demo.R;

public class LoginActivity extends AppCompatActivity {

    private TextView tv_to_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_to_register = findViewById(R.id.to_register);
        tv_to_register.setPaintFlags(tv_to_register.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }
}