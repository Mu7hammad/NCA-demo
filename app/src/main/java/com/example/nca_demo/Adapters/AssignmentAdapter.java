package com.example.nca_demo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nca_demo.Models.AssignmentItem;
import com.example.nca_demo.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.MyViewHolder> {

    private Context context;
    private List<AssignmentItem> items;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout main_layout;
        TextView title_tv, description_tv;

        public MyViewHolder(View view) {
            super(view);
            main_layout = view.findViewById(R.id.main_layout);
            title_tv = view.findViewById(R.id.title_tv);
            description_tv = view.findViewById(R.id.description_tv);
        }
    }

    public AssignmentAdapter(Context context, List<AssignmentItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final AssignmentItem p = items.get(position);
        holder.title_tv.setText(p.getTitle());
        holder.description_tv.setText(p.getDescription());

        holder.main_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Open item: "+p.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_item, parent, false);
        return new MyViewHolder(v);
    }
}
