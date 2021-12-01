package com.example.nca_demo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nca_demo.Models.SubjectItem;
import com.example.nca_demo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SubjectsAdapter extends ArrayAdapter<SubjectItem> {

    private Context mContext;
    private int resourseLayout;
    List<SubjectItem> items;
    int[] colors = {R.color.dark_green, R.color.red, R.color.yellow, R.color.purple, R.color.light_blue};
    int color_position = 0;

    public SubjectsAdapter(Context mContext, int resourseLayout, List<SubjectItem> items) {
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

        SubjectItem p = items.get(position);
        TextView title_tv = v.findViewById(R.id.title_tv);
        TextView code_tv = v.findViewById(R.id.code_tv);
        TextView first_letter_in_title_tv = v.findViewById(R.id.first_letter_in_title_tv);
        TextView position_tv = v.findViewById(R.id.position_tv);
        final RelativeLayout back_rel = v.findViewById(R.id.back_rel);
        load_color(back_rel, position);
        title_tv.setText(p.getTitle());
        code_tv.setText("Code: " + p.getCode());
        first_letter_in_title_tv.setText(p.getTitle());
        position_tv.setText(position+1+"");

        return v;
    }

    private void load_color(RelativeLayout back_rel, int position) {
        if (position != 0 && !items.get(position).getTitle().equals(items.get(position - 1).getTitle())) {
            /* the previous position has different name, lets change color position */
            color_position++;
            if (color_position > colors.length - 1) {
                /* color position is out of array size, reset position */
                color_position = 0;
            }
        }
        back_rel.setBackground(mContext.getResources().getDrawable(colors[color_position]));
    }
}