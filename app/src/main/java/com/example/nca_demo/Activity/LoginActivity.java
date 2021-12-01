package com.example.nca_demo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nca_demo.R;
import com.sdsmdg.tastytoast.TastyToast;

public class LoginActivity extends AppCompatActivity {

    private EditText et_login_code, et_login_password;

    private TextView tv_to_register;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_login_code = findViewById(R.id.et_login_code);
        et_login_password = findViewById(R.id.et_login_password);

        tv_to_register = findViewById(R.id.to_register);
        btn_login = findViewById(R.id.btn_login);

        tv_to_register.setPaintFlags(tv_to_register.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = et_login_code.getText().toString();
                if (code.isEmpty()) {
                    TastyToast.makeText(getApplicationContext(), "fill the field"
                            , TastyToast.LENGTH_LONG, TastyToast.INFO);
                } else {
                    char first_letter = code.charAt(0);
                    if (first_letter != 'c') {
                        TastyToast.makeText(getApplicationContext(), "Code must starts with C"
                                , TastyToast.LENGTH_LONG, TastyToast.ERROR);
                    } else {
                        TastyToast.makeText(getApplicationContext(), "Login Successfully", TastyToast.LENGTH_LONG
                                , TastyToast.SUCCESS);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                }
            }
        });

    }
}