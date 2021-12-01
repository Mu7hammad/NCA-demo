package com.example.nca_demo.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nca_demo.Models.LessonPlanningItem;
import com.example.nca_demo.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class LessonPlanningAdapter extends RecyclerView.Adapter<LessonPlanningAdapter.MyViewHolder> {

    private Context context;
    private List<LessonPlanningItem> items;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView pointer_img;
        TextView text_tv;

        public MyViewHolder(View view) {
            super(view);
            pointer_img = view.findViewById(R.id.pointer_img);
            text_tv = view.findViewById(R.id.text_tv);

        }
    }

    public LessonPlanningAdapter(Context context, List<LessonPlanningItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final LessonPlanningItem p = items.get(position);
        holder.text_tv.setText(p.getText());
        if(p.isDone()){
            holder.pointer_img.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.dark_green)));
        } else {
            holder.pointer_img.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.default_gray)));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_planning_item, parent, false);
        return new MyViewHolder(v);
    }
}
