package com.example.nca_demo.Screens;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nca_demo.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ContactUs extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbar_title;
    ImageView facebook_btn, whatsapp_btn, youtube_btn;
    Activity activity;
    ImageButton call_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        declaration();
        read_toolbar();
        onClick();
    }

    private void onClick() {
        facebook_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    activity.getPackageManager().getPackageInfo("com.facebook.katana", PackageManager.GET_ACTIVITIES);
                    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("fb://page/176552522737276")));
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/CIS.EDU1")));
                }
            }
        });

        whatsapp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    activity.getPackageManager().getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://api.whatsapp.com/send?phone=+201207650873")));
                } catch (Exception e) {
                }
            }
        });

        youtube_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCoRUJ2St0Bx7WjKdbTqOvkQ")));
            }
        });

        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 0);
                } else {
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:19622")));
                }
            }
        });
    }

    private void read_toolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_round_arrow_back_24);
        upArrow.setColorFilter(getResources().getColor(R.color.default_gray), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void declaration() {
        toolbar = findViewById(R.id.toolbar);
        toolbar_title = findViewById(R.id.toolbar_title);
        facebook_btn = findViewById(R.id.facebook_btn);
        whatsapp_btn = findViewById(R.id.whatsapp_btn);
        youtube_btn = findViewById(R.id.youtube_btn);
        call_btn = findViewById(R.id.call_btn);
        activity = this;
    }
}