package com.example.nca_demo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nca_demo.Adapters.ProfileAdapter;
import com.example.nca_demo.Others.ListenDataChanged;
import com.example.nca_demo.MainActivity;
import com.example.nca_demo.Models.ListItem;
import com.example.nca_demo.R;
import com.example.nca_demo.Screens.Login;
import com.example.nca_demo.Others.StaticVar;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;

public class FourthFragment extends Fragment {

    public FourthFragment() {
        // Required empty public constructor
    }

    public static FourthFragment newInstance(String param1, String param2) {
        FourthFragment fragment = new FourthFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ListView list_view;
    List<ListItem> profile_items = new ArrayList<>();
    LinearLayout logout_btn;
    TextView username_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fourth_fragment, container, false);

        declaration(view);
        check_all_data_blocs_loaded();
        onClick();

        return view;
    }

    private void check_all_data_blocs_loaded() {
        if (MainActivity.main_data_loaded) {
            load_username();
            get_profile_items();
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

    private void load_username() {
        username_tv.setText(StaticVar.student.getName());
    }

    private void onClick() {
        logout_btn.setOnClickListener(view -> {
            clear_user_data();
            startActivity(new Intent(getActivity(), Login.class));
            getActivity().finish();
        });
    }

    private void clear_user_data() {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE).edit();
        editor.putBoolean("login", false);
        editor.putString("code", "");
        editor.commit();
    }

    private void get_profile_items() {
        profile_items.clear();
        profile_items.add(new ListItem("Code", StaticVar.student.getCode()));
        profile_items.add(new ListItem("GPA", StaticVar.student.getGpa() + ""));
        profile_items.add(new ListItem("Specialization", StaticVar.student.getSpecialization()));
        profile_items.add(new ListItem("Level", StaticVar.student.getLevel()));
        profile_items.add(new ListItem("Classification", StaticVar.student.getClassification()));
    }

    private void load_adapter() {
        ProfileAdapter itemsAdapter = new ProfileAdapter(this.getContext(), R.layout.profile_item, profile_items);
        list_view.setAdapter(itemsAdapter);
    }

    private void declaration(View v) {
        list_view = v.findViewById(R.id.list_view);
        logout_btn = v.findViewById(R.id.logout_btn);
        username_tv = v.findViewById(R.id.username_tv);
    }
}
