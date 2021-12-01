package com.example.nca_demo.Screens;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nca_demo.Adapters.ResultAdapter;
import com.example.nca_demo.Others.ListenDataChanged;
import com.example.nca_demo.Models.ResultItem;
import com.example.nca_demo.R;
import com.example.nca_demo.Others.StaticVar;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Results extends AppCompatActivity {

    Toolbar toolbar;
    TextView toolbar_title;
    TextView no_result_tv, result_title;
    LinearLayout result_lin;
    ListView list_view;
    ProgressBar progress;
    ListenDataChanged listenDataLoaded = new ListenDataChanged();
    List<ResultItem> result_items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        declaration();
        read_toolbar();
        get_result_items();
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

    private void get_result_items() {
        /* TODO Get data from database */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                result_items.clear();
                result_items.add(new ResultItem("ex 1", "B+"));
                result_items.add(new ResultItem("ex 2", "D"));
                result_items.add(new ResultItem("ex 3", "C+"));
                result_items.add(new ResultItem("ex 4", "F"));
                result_items.add(new ResultItem("ex 5", "A"));
                listenDataLoaded.setBo(!listenDataLoaded.isBo());
            }
        }, StaticVar.delay);
    }

    private void load_adapter() {
        if (result_items.size() > 0) {
            /* adapter */
            ResultAdapter itemsAdapter = new ResultAdapter(Results.this, R.layout.result_item, result_items);
            list_view.setAdapter(itemsAdapter);
            /* load ui */
            result_lin.setVisibility(View.VISIBLE);
            result_title.setVisibility(View.VISIBLE);
            result_title.setText("Second term results");
        } else {
            no_result_tv.setVisibility(View.VISIBLE);
        }
        progress.setVisibility(View.GONE);
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
        no_result_tv = findViewById(R.id.no_result_tv);
        result_lin = findViewById(R.id.result_lin);
        list_view = findViewById(R.id.list_view);
        result_title = findViewById(R.id.result_title);
        progress = findViewById(R.id.progress);
    }
}