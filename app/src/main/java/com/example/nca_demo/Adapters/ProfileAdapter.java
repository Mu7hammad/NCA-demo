package com.example.nca_demo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nca_demo.Models.ListItem;
import com.example.nca_demo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProfileAdapter extends ArrayAdapter<ListItem> {

    private Context mContext;
    private int resourseLayout;
    List<ListItem> items;

    public ProfileAdapter(Context mContext, int resourseLayout, List<ListItem> items) {
        super(mContext, resourseLayout, items);
        this.resourseLayout = resourseLayout;
        this.mContext = mContext;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(resourseLayout, null);
        }
        ListItem p = items.get(position);
        TextView title_tv = v.findViewById(R.id.title_tv);
        TextView value_tv = v.findViewById(R.id.value_tv);
        title_tv.setText(p.getTitle());
        value_tv.setText(p.getValue());

        return v;
    }
}