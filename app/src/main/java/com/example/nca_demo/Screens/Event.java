package com.example.nca_demo.Screens;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nca_demo.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Event extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbar_title;
    TextView event_title, event_date, event_description;
    ImageView event_img;
    String title, date, description, image_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        declaration();
        read_toolbar();
        load_ui();
    }

    private void load_ui() {
        event_title.setText(title);
        event_date.setText(date);
        event_description.setText(description);
        if (!image_url.equals("")){
            Glide.with(this).load(image_url).into(event_img);
        } else {
            Glide.with(this).load(getResources().getDrawable(R.drawable.ic_launcher)).into(event_img);
        }
    }

    private void declaration() {
        toolbar = findViewById(R.id.toolbar);
        toolbar_title = findViewById(R.id.toolbar_title);
        event_title = findViewById(R.id.event_title);
        event_date = findViewById(R.id.event_date);
        event_description = findViewById(R.id.event_description);
        event_img = findViewById(R.id.event_img);
        title = getIntent().getStringExtra("title");
        date = getIntent().getStringExtra("date");
        description = getIntent().getStringExtra("description");
        image_url = getIntent().getStringExtra("image_url");
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
}