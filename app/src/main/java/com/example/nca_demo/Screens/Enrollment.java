package com.example.nca_demo.Screens;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nca_demo.Adapters.SubjectsAdapter;
import com.example.nca_demo.Others.ListenDataChanged;
import com.example.nca_demo.Models.SubjectItem;
import com.example.nca_demo.R;
import com.example.nca_demo.Others.StaticVar;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Enrollment extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbar_title;
    ListView list_view;
    TextView nothing_tv;
    ProgressBar progress;
    ListenDataChanged listenDataLoaded = new ListenDataChanged();
    List<SubjectItem> subjects_items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);

        declaration();
        read_toolbar();
        get_subjects();
        check_data_loaded();
    }

    private void check_data_loaded() {
        listenDataLoaded.setListener(new ListenDataChanged.ChangeListener() {
            @Override
            public void onChange() {
                load_adapter();
            }
        });
    }

    private void get_subjects() {
        /* TODO Get data from database */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                subjects_items.clear();
                subjects_items.add(new SubjectItem("Network programming", "exCode1"));
                subjects_items.add(new SubjectItem("Artificial intelligence", "exCode2"));
                subjects_items.add(new SubjectItem("Internet technology", "exCode3"));
                subjects_items.add(new SubjectItem("Software engineering 2", "exCode4"));
                listenDataLoaded.setBo(!listenDataLoaded.isBo());
            }
        }, StaticVar.delay);
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

    private void load_adapter() {
                if (subjects_items.size() > 0) {
                    SubjectsAdapter itemsAdapter = new SubjectsAdapter(Enrollment.this, R.layout.subject_item, subjects_items);
                    list_view.setAdapter(itemsAdapter);

                    /* delay to repair scroll bug */
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            list_view.setVisibility(View.VISIBLE);
                            progress.setVisibility(View.GONE);
                        }
                    }, 0);


                } else {
                    nothing_tv.setVisibility(View.VISIBLE);
                    progress.setVisibility(View.GONE);
                }
    }

    private void declaration() {
        toolbar = findViewById(R.id.toolbar);
        toolbar_title = findViewById(R.id.toolbar_title);
        list_view = findViewById(R.id.list_view);
        nothing_tv = findViewById(R.id.nothing_tv);
        progress = findViewById(R.id.progress);
    }
}