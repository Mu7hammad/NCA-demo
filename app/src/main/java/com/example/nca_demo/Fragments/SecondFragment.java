package com.example.nca_demo.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nca_demo.Adapters.TimeTableAdapter;
import com.example.nca_demo.Others.ListenDataChanged;
import com.example.nca_demo.MainActivity;
import com.example.nca_demo.R;
import com.example.nca_demo.Others.StaticVar;

import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ListView list_view;
    TextView nothing_tv;
    ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        declaration(view);
        check_all_data_blocs_loaded();

        return view;
    }

    private void check_all_data_blocs_loaded() {
        if (MainActivity.main_data_loaded) {
            load_adapter();
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

    private void load_adapter() {
        if (StaticVar.time_table_items.size() > 0) {
            TimeTableAdapter itemsAdapter = new TimeTableAdapter(this.getContext(), R.layout.time_table_item, StaticVar.time_table_items);
            list_view.setAdapter(itemsAdapter);
            list_view.setVisibility(View.VISIBLE);
        } else {
            nothing_tv.setVisibility(View.VISIBLE);
        }
        progress.setVisibility(View.GONE);
    }

    private void declaration(View v) {
        list_view = v.findViewById(R.id.list_view);
        nothing_tv = v.findViewById(R.id.nothing_tv);
        progress = v.findViewById(R.id.progress);
    }
}
