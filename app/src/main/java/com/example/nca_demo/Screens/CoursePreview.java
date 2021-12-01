package com.example.nca_demo.Screens;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nca_demo.Others.BottomNavigationBehavior;
import com.example.nca_demo.Fragments.CourseFirstFragment;
import com.example.nca_demo.Fragments.CourseSecondFragment;
import com.example.nca_demo.Fragments.CourseThirdFragment;
import com.example.nca_demo.Others.ListenDataChanged;
import com.example.nca_demo.MainActivity;
import com.example.nca_demo.Models.AssignmentItem;
import com.example.nca_demo.Models.CourseItem;
import com.example.nca_demo.Models.LessonPlanningItem;
import com.example.nca_demo.Models.MaterialItem;
import com.example.nca_demo.Models.TimeTableItem;
import com.example.nca_demo.R;
import com.example.nca_demo.Others.StaticVar;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CoursePreview extends AppCompatActivity {

    Toolbar toolbar;
    TextView progress_tv, date_tv, time_tv, instructor_name_tv, room_tv, attendance_value_tv;
    ProgressBar progress_bar;
    String course_name;
    TimeTableItem current_course;
    int color;
    LinearLayout back_lin;
    ListenDataChanged listenDataLoaded = new ListenDataChanged();
    List<CourseItem> course_items = new ArrayList<>();
    BottomNavigationView navigation;
    CollapsingToolbarLayout collapsing_toolbar;
    int blocs_of_data = 3, blocs_loaded = 0;
    public static boolean main_data_loaded = false;
    public static ListenDataChanged listenDataChanged = new ListenDataChanged();
    public static List<MaterialItem> material_items = new ArrayList<>();
    public static List<AssignmentItem> assignments_items = new ArrayList<>();
    public static List<LessonPlanningItem> lesson_planning_items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_preview);

        declaration();
        check_language();
        read_toolbar();
        load_back_color();
        get_course_items();
        check_data_loaded();
        load_blocs_of_data();
        load_bottom_navigation();
        loadFragment(new CourseFirstFragment());
        onClick();
    }

    private void load_blocs_of_data() {
        get_material();
        get_assignment();
        get_lesson_planning();
    }

    private void onClick() {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.first_nav:
                        loadFragment(new CourseFirstFragment());
                        return true;
                    case R.id.second_nav:
                        loadFragment(new CourseSecondFragment());
                        return true;
                    case R.id.third_nav:
                        loadFragment(new CourseThirdFragment());
                        return true;
                }
                return false;
            }
        });
    }

    private void check_all_data_loaded() {
        blocs_loaded++;
        if (blocs_loaded >= blocs_of_data) {
            main_data_loaded = true;
            listenDataChanged.setBo(!listenDataChanged.isBo());
        }
    }

    private void get_material() {
        /* TODO Get data from database */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                material_items.clear();
                material_items.add(new MaterialItem("example material text 1"));
                material_items.add(new MaterialItem("example material text 2"));
                material_items.add(new MaterialItem("example material text 3"));
                material_items.add(new MaterialItem("example material text 4"));
                material_items.add(new MaterialItem("example material text 5"));
                material_items.add(new MaterialItem("example material text 6"));
                material_items.add(new MaterialItem("example material text 7"));
                material_items.add(new MaterialItem("example material text 8"));
                material_items.add(new MaterialItem("example material text 9"));
                material_items.add(new MaterialItem("example material text 10"));
                check_all_data_loaded();
            }
        }, StaticVar.delay);
    }

    private void get_assignment() {
        /* TODO Get data from database */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                assignments_items.clear();
                assignments_items.add(new AssignmentItem("example title 1", "example description 1"));
                assignments_items.add(new AssignmentItem("example title 2", "example description 2"));
                assignments_items.add(new AssignmentItem("example title 3", "example description 3"));
                assignments_items.add(new AssignmentItem("example title 4", "example description 4"));
                assignments_items.add(new AssignmentItem("example title 5", "example description 5"));
                assignments_items.add(new AssignmentItem("example title 6", "example description 6"));
                assignments_items.add(new AssignmentItem("example title 7", "example description 7"));
                assignments_items.add(new AssignmentItem("example title 8", "example description 8"));
                assignments_items.add(new AssignmentItem("example title 9", "example description 9"));
                assignments_items.add(new AssignmentItem("example title 10", "example description 10"));
                check_all_data_loaded();
            }
        }, StaticVar.delay * 2);
    }

    private void get_lesson_planning() {
        /* TODO Get data from database */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                lesson_planning_items.clear();
                lesson_planning_items.add(new LessonPlanningItem("example lesson planning text 1", true));
                lesson_planning_items.add(new LessonPlanningItem("example lesson planning text 2", true));
                lesson_planning_items.add(new LessonPlanningItem("example lesson planning text 3", true));
                lesson_planning_items.add(new LessonPlanningItem("example lesson planning text 4", true));
                lesson_planning_items.add(new LessonPlanningItem("example lesson planning text 5", false));
                lesson_planning_items.add(new LessonPlanningItem("example lesson planning text 6", false));
                lesson_planning_items.add(new LessonPlanningItem("example lesson planning text 7", false));
                lesson_planning_items.add(new LessonPlanningItem("example lesson planning text 8", false));
                lesson_planning_items.add(new LessonPlanningItem("example lesson planning text 9", true));
                lesson_planning_items.add(new LessonPlanningItem("example lesson planning text 10", false));
                check_all_data_loaded();
            }
        }, StaticVar.delay * 3);
    }

    private void check_data_loaded() {
        listenDataLoaded.setListener(new ListenDataChanged.ChangeListener() {
            @Override
            public void onChange() {
                load_current_course();
                load_ui();
            }
        });
    }

    private void get_course_items() {
        /* TODO Get data from database */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                course_items.clear();
                course_items.add(new CourseItem("Network programming", 5));
                course_items.add(new CourseItem("Artificial intelligence", 4));
                course_items.add(new CourseItem("Internet technology", 1));
                course_items.add(new CourseItem("Software engineering 2", 3));
                listenDataLoaded.setBo(!listenDataLoaded.isBo());
            }
        }, StaticVar.delay);
    }

    private void load_back_color() {
        getWindow().setStatusBarColor(color);
        toolbar.setBackgroundColor(color);
        back_lin.setBackgroundColor(color);
    }

    private void load_current_course() {
        for (int i = 0; i < StaticVar.time_table_items.size(); i++) {
            if (StaticVar.time_table_items.get(i).getTitle().equals(course_name)) {
                /* that's current course, load it */
                current_course = StaticVar.time_table_items.get(i);
            }
        }
    }

    private void load_ui() {
        collapsing_toolbar.setTitle(course_name);
        int progress = MainActivity.get_course_progress(current_course.getDate(), current_course.getTime());
        progress_tv.setText(progress + "%");
        progress_bar.setProgress(progress);
        date_tv.setText(current_course.getDate());
        time_tv.setText(current_course.getTime() + "  -  " + increase_time_by_hour_and_half(current_course.getTime()));
        instructor_name_tv.setText(current_course.getInstructor_name());
        room_tv.setText("Room: " + current_course.getRoom());
        attendance_value_tv.setText(get_attendance() + "");
    }

    private int get_attendance() {
        for (int i = 0; i < course_items.size(); i++) {
            if (course_items.get(i).getTitle().equals(course_name)) {
                return course_items.get(i).getAttendance();
            }
        }
        return 0;
    }

    private String increase_time_by_hour_and_half(String time) {
        List<String> arr = Arrays.asList(time.toLowerCase().replaceAll(" am", "").replaceAll(" pm", "").split(":"));
        int hour = Integer.parseInt(arr.get(0)), min = Integer.parseInt(arr.get(1));
        if (min == 0) {
            hour += 1;
            min = 30;
        } else {
            hour += 2;
            min = 0;
        }
        if (hour > 12) {
            hour -= 12;
        }
        return hour > 4 ? hour + ":" + min + " AM" : hour + ":" + min + " PM";
    }

    private void declaration() {
        toolbar = findViewById(R.id.toolbar);
        progress_tv = findViewById(R.id.progress_tv);
        date_tv = findViewById(R.id.date_tv);
        time_tv = findViewById(R.id.time_tv);
        instructor_name_tv = findViewById(R.id.instructor_name_tv);
        room_tv = findViewById(R.id.room_tv);
        attendance_value_tv = findViewById(R.id.attendance_value_tv);
        progress_bar = findViewById(R.id.progress_bar);
        course_name = getIntent().getStringExtra("course_name");
        color = getIntent().getIntExtra("color", 0);
        back_lin = findViewById(R.id.back_lin);
        navigation = findViewById(R.id.navigation);
        collapsing_toolbar = findViewById(R.id.collapsing_toolbar);
    }

    private void load_bottom_navigation() {
        navigation.setItemIconTintList(ColorStateList.valueOf(Color.WHITE));
        navigation.setItemTextColor(ColorStateList.valueOf(Color.WHITE));
        navigation.setBackgroundColor(color);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
    }

    private void check_language() {
        if (StaticVar.Arabic) {
//            navigation.getMenu().findItem(R.id.first_nav).setTitle("Main");
//            navigation.getMenu().findItem(R.id.second_nav).setTitle("Second");
//            navigation.getMenu().findItem(R.id.third_nav).setTitle("Third");
//            navigation.getMenu().findItem(R.id.fourth_nav).setTitle("Fourth");
        }
    }

    private void loadFragment(Fragment fragment) {
        if (!(getSupportFragmentManager().getFragments().toString()).contains((fragment.toString().substring(0, 8)))) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    private void read_toolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_round_arrow_back_24);
        upArrow.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}