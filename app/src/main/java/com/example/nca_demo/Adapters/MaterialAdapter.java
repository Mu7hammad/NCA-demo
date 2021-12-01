package com.example.nca_demo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nca_demo.Models.MaterialItem;
import com.example.nca_demo.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MyViewHolder> {

    private Context context;
    private List<MaterialItem> items;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout main_layout;
        TextView text_tv;

        public MyViewHolder(View view) {
            super(view);
            main_layout = view.findViewById(R.id.main_layout);
            text_tv = view.findViewById(R.id.text_tv);

        }
    }

    public MaterialAdapter(Context context, List<MaterialItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final MaterialItem p = items.get(position);
        holder.text_tv.setText(p.getText());

        holder.main_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Open item: "+p.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_item, parent, false);
        return new MyViewHolder(v);
    }
}
