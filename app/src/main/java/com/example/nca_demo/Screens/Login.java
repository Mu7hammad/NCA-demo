package com.example.nca_demo.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nca_demo.MainActivity;
import com.example.nca_demo.Others.StaticVar;
import com.example.nca_demo.R;
import com.sdsmdg.tastytoast.TastyToast;

public class Login extends AppCompatActivity {

    private EditText et_login_code, et_login_password;
    private TextView tv_to_register;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        declaration();
        onClick();
    }

    private void onClick() {
        tv_to_register.setOnClickListener(v -> startActivity(new Intent(Login.this, Register.class)));

        btn_login.setOnClickListener(v -> {
            String code = et_login_code.getText().toString();
            if (code.isEmpty()) {
                show_message("fill the field", false);
            } else {
                char first_letter = code.charAt(0);
                if (first_letter != 'c') {
                    show_message("Code must starts with C", false);
                } else if (code.toLowerCase().equals("c1700832") || code.toLowerCase().equals("c1")){
                    show_message("Login Successfully", true);
                    startActivity(new Intent(Login.this, MainActivity.class));
                    store_user_data(code);
                    StaticVar.student.setCode(code);
                    finish();
                } else {
                    show_message("User not founded!", false);
                }
            }
        });
    }

    private void show_message(String message, boolean success){
        TastyToast.makeText(getApplicationContext(), message, TastyToast.LENGTH_LONG, success?TastyToast.SUCCESS:TastyToast.ERROR);
    }

    private void declaration() {
        et_login_code = findViewById(R.id.et_login_code);
        et_login_password = findViewById(R.id.et_login_password);
        tv_to_register = findViewById(R.id.to_register);
        btn_login = findViewById(R.id.btn_login);
        tv_to_register.setPaintFlags(tv_to_register.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void store_user_data(String code) {
        SharedPreferences.Editor editor = getSharedPreferences("UserData", Context.MODE_PRIVATE).edit();
        editor.putBoolean("login", true);
        editor.putString("code", code);
        editor.commit();
    }
}