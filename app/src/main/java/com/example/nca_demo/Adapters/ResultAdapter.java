package com.example.nca_demo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nca_demo.Models.ResultItem;
import com.example.nca_demo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ResultAdapter extends ArrayAdapter<ResultItem> {

    private Context mContext;
    private int resourseLayout;
    List<ResultItem> items;

    public ResultAdapter(Context mContext, int resourseLayout, List<ResultItem> items) {
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

        ResultItem p = items.get(position);
        TextView subject_tv = v.findViewById(R.id.subject_tv);
        TextView result_tv = v.findViewById(R.id.result_tv);
        subject_tv.setText(p.getSubject());
        result_tv.setText(p.getResult());
        if (p.getResult().equals("F")){
            result_tv.setBackgroundColor(mContext.getResources().getColor(R.color.red));
        } else {
            result_tv.setBackgroundColor(mContext.getResources().getColor(R.color.dark_green));
        }

        return v;
    }
}