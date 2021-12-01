package com.example.nca_demo.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nca_demo.Adapters.LessonPlanningAdapter;
import com.example.nca_demo.Others.ListenDataChanged;
import com.example.nca_demo.R;
import com.example.nca_demo.Screens.CoursePreview;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CourseThirdFragment extends Fragment {

    public CourseThirdFragment() {
        // Required empty public constructor
    }

    public static CourseThirdFragment newInstance(String param1, String param2) {
        CourseThirdFragment fragment = new CourseThirdFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView recycler_view;
    ProgressBar progress;
    TextView nothing_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_third_fragment, container, false);

        declaration(view);
        check_all_data_blocs_loaded();

        return view;
    }

    private void check_all_data_blocs_loaded() {
        if (CoursePreview.main_data_loaded) {
            load_adapter();
        } else {
            CoursePreview.listenDataChanged.setListener(new ListenDataChanged.ChangeListener() {
                @Override
                public void onChange() {
                    /* reload method again */
                    check_all_data_blocs_loaded();
                }
            });
        }
    }

    private void load_adapter() {
        if (CoursePreview.lesson_planning_items.size() > 0) {
            LessonPlanningAdapter itemsAdapter = new LessonPlanningAdapter(getActivity(), CoursePreview.lesson_planning_items);
            recycler_view.setAdapter(itemsAdapter);
            /* don't forget linear layout manager with recycler view */
            recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
            recycler_view.setVisibility(View.VISIBLE);
        } else {
            nothing_tv.setVisibility(View.VISIBLE);
        }
        progress.setVisibility(View.GONE);
    }

    private void declaration(View view) {
        recycler_view = view.findViewById(R.id.recycler_view);
        nothing_tv = view.findViewById(R.id.nothing_tv);
        progress = view.findViewById(R.id.progress);
    }
}
