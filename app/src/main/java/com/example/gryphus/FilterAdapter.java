package com.example.gryphus;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.myViewHolder>{

    private ArrayList<Filter> filterList;



    public FilterAdapter(ArrayList<Filter> filterList) {
        this.filterList = filterList;
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView nameText;
        private ImageView image;

        public myViewHolder(final View v){
            super(v);
            nameText = v.findViewById(R.id.info);
            image = v.findViewById(R.id.imageView);
        }
    }


    @NonNull
    @Override
    public FilterAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(parent.getContext(), "click successful", Toast.LENGTH_SHORT).show();
            }
        });

        return new FilterAdapter.myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterAdapter.myViewHolder holder, int position) {
        String filterType = filterList.get(position).getType();
        holder.nameText.setText(filterType);
        Drawable icon = filterList.get(position).getIcon();
        holder.image.setImageDrawable(icon);

    }

    @Override
    public int getItemCount() {
        return filterList.size();
    }
}
