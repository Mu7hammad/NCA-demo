package com.example.nca_demo.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nca_demo.Adapters.EventsAdapter;
import com.example.nca_demo.Adapters.TimeTableAdapter;
import com.example.nca_demo.Others.ListenDataChanged;
import com.example.nca_demo.MainActivity;
import com.example.nca_demo.Models.EventItem;
import com.example.nca_demo.Models.TimeTableItem;
import com.example.nca_demo.R;
import com.example.nca_demo.Screens.ContactUs;
import com.example.nca_demo.Screens.Enrollment;
import com.example.nca_demo.Screens.Fees;
import com.example.nca_demo.Screens.GPA;
import com.example.nca_demo.Screens.Results;
import com.example.nca_demo.Others.StaticVar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FirstFragment extends Fragment {

    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Calendar calendar;
    String[] days_array = new String[]{"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    TextView today_lectures_tv, nothing_lectures_tv, welcome_text;
    List<TimeTableItem> lectures_list = new ArrayList<>();
    ListView list_view;
    RecyclerView list_view2;
    TextView nothing_events_tv;
    ImageView menu_btn;
    boolean menu_opened = false;
    LinearLayout menu_lin, item1_lin, item2_lin, item3_lin;
    TextView fees_value_tv, gpa_value_tv;
    LinearLayout fees_layout, gpa_layout;
    ProgressBar lectures_progress, events_progress;
    NestedScrollView scroll_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container, false);

        declaration(view);
        check_all_data_blocs_loaded();
        onClick();

        return view;
    }

    private void check_all_data_blocs_loaded() {
        if (MainActivity.main_data_loaded) {
            load_welcome_text();
            load_lectures();
            load_events();
            load_fees();
            load_gpa();
        } else {
            MainActivity.listenDataChanged.setListener(new ListenDataChanged.ChangeListener() {
                @Override
                public void onChange() {
                    /* reload method again */
                    check_all_data_blocs_loaded();
                }
            });
        }
    }

    private void load_gpa() {
        gpa_value_tv.setText(StaticVar.student.getGpa() + "");
    }

    private void load_fees() {
        fees_value_tv.setText(StaticVar.student.getFees() + " EGP");
        if (StaticVar.student.getFees() == 0) {
            fees_value_tv.setTextColor(getResources().getColor(R.color.dark_green));
        }
    }

    private void load_events() {
        if (StaticVar.events_items.size() > 0) {
            sort_list_by_date();
            Collections.reverse(StaticVar.events_items);
            /* adapter */
            EventsAdapter itemsAdapter = new EventsAdapter(getActivity(), StaticVar.events_items);
            list_view2.setAdapter(itemsAdapter);
            /* don't forget linear layout manager with recycler view */
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.HORIZONTAL);
            list_view2.setLayoutManager(llm);
            /* update ui */
            events_progress.setVisibility(View.GONE);
            list_view2.setVisibility(View.VISIBLE);
        } else {
            /* update ui */
            events_progress.setVisibility(View.GONE);
            nothing_events_tv.setVisibility(View.VISIBLE);
        }
    }

    private void sort_list_by_date() {
        Collections.sort(StaticVar.events_items, new Comparator<EventItem>() {
            @Override
            public int compare(EventItem o1, EventItem o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
    }

    private void onClick() {
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menu_opened) {
                    close_menu();
                } else {
                    open_menu();
                }
            }
        });

        item1_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Enrollment.class));
                close_menu();
            }
        });

        item2_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Results.class));
                close_menu();
            }
        });

        item3_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ContactUs.class));
                close_menu();
            }
        });

        fees_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Fees.class));
            }
        });

        gpa_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), GPA.class));
            }
        });
    }

    private void open_menu() {
        expand(menu_lin);
        menu_opened = true;
        menu_lin.setVisibility(View.VISIBLE);
        menu_btn.setImageResource(R.drawable.ic_round_menu_open_24);
    }

    private void close_menu() {
        collapse(menu_lin);
        menu_opened = false;
        menu_btn.setImageResource(R.drawable.ic_round_menu_24);
    }

    private void load_welcome_text() {
        welcome_text.setText(getString(R.string.welcome) + Arrays.asList(StaticVar.student.getName().split(" ")).get(0));
    }

    private void load_lectures() {
        lectures_list.clear();
        /* check if we have classes today */
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) + 1;
        if (day_of_week > 7) {
            day_of_week = 1;
        }
        String today = days_array[day_of_week - 1];
        Calendar tomroow_cal = Calendar.getInstance();
        tomroow_cal.add(Calendar.DAY_OF_YEAR, 1);
        int day_of_week_t = tomroow_cal.get(Calendar.DAY_OF_WEEK) + 1;
        if (day_of_week_t > 7) {
            day_of_week_t = 1;
        }
        String tomorrow = days_array[day_of_week_t - 1];
        ///////////
        if (today_is_end()) {
            today_lectures_tv.setText(R.string.lectures_tomorrow);
            /* load tomorrow lectures */
            for (int i = 0; i < StaticVar.time_table_items.size(); i++) {
                if (StaticVar.time_table_items.get(i).getDate().equals(tomorrow)) {
                    lectures_list.add(StaticVar.time_table_items.get(i));
                }
            }
        } else {
            /* load today lectures */
            for (int i = 0; i < StaticVar.time_table_items.size(); i++) {
                if (StaticVar.time_table_items.get(i).getDate().equals(today)) {
                    lectures_list.add(StaticVar.time_table_items.get(i));
                }
            }
        }
        load_adapter();
    }

    private void load_adapter() {
        if (lectures_list.size() > 0) {
            TimeTableAdapter itemsAdapter = new TimeTableAdapter(this.getContext(), R.layout.time_table_item, lectures_list);
            list_view.setAdapter(itemsAdapter);
            list_view.setVisibility(View.VISIBLE);
            lectures_progress.setVisibility(View.GONE);
        } else {
            lectures_progress.setVisibility(View.GONE);
            nothing_lectures_tv.setVisibility(View.VISIBLE);
        }
    }

    private void declaration(View v) {
        calendar = Calendar.getInstance();
        today_lectures_tv = v.findViewById(R.id.today_lectures_tv);
        nothing_lectures_tv = v.findViewById(R.id.nothing_lectures_tv);
        list_view = v.findViewById(R.id.list_view);
        list_view2 = v.findViewById(R.id.list_view2);
        welcome_text = v.findViewById(R.id.welcome_text);
        menu_btn = v.findViewById(R.id.menu_btn);
        menu_lin = v.findViewById(R.id.menu_lin);
        item1_lin = v.findViewById(R.id.item1_lin);
        item2_lin = v.findViewById(R.id.item2_lin);
        item3_lin = v.findViewById(R.id.item3_lin);
        nothing_events_tv = v.findViewById(R.id.nothing_events_tv);
        fees_value_tv = v.findViewById(R.id.fees_value_tv);
        gpa_value_tv = v.findViewById(R.id.gpa_value_tv);
        fees_layout = v.findViewById(R.id.fees_layout);
        gpa_layout = v.findViewById(R.id.gpa_layout);
        lectures_progress = v.findViewById(R.id.lectures_progress);
        events_progress = v.findViewById(R.id.events_progress);
        scroll_view = v.findViewById(R.id.scroll_view);
    }

    private boolean today_is_end() {
        return calendar.get(Calendar.HOUR_OF_DAY) >= 16;
    }

    private void expand(final View v) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) v.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = (v.getMeasuredHeight());
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    private void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
/* TODO Models
 * student have :
 *   1- name
 *   2- code
 *   3- gpa
 *   4- specialization
 *   5- level
 *   6- classification
 *   7- fees
 * Time table have :
 *   1- title
 *   2- date
 *   3- time
 *   4- room
 *   5- instructor
 * Event have :
 *   1- title
 *   2- description
 *   3- date
 *   4- image url
 * Result have :
 *   1- subject
 *   2- result
 * Course have :
 *   1- title
 *   2- finished lectures
 *   3- attendance
 * Subject have :
 *   1- title
 *   2- code
 *
 * */