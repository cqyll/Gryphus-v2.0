package com.example.gryphus;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class productAdapter extends RecyclerView.Adapter<productAdapter.myViewHolder> {


    private ArrayList<Product> productList;

//    public AccountModel account;
//
//    public ArrayList<Product> favsList = new ArrayList<Product>();



    public productAdapter(ArrayList<Product> productList) {
        this.productList = productList;
    }



    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_product, parent, false);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(parent.getContext(), "click successful", Toast.LENGTH_SHORT).show();
            }
        });

      return new myViewHolder(itemView);
    }      


    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Product currentProduct = productList.get(position);
        String productName = currentProduct.getName();
        holder.nameText.setText(productName);
        Drawable icon = currentProduct.getIcon();
        holder.image.setImageDrawable(icon);




        SharedPreferences pref = holder.favButton.getContext().getSharedPreferences("favDB", Context.MODE_PRIVATE);
        SharedPreferences userPref = holder.favButton.getContext().getSharedPreferences("accountDB", Context.MODE_PRIVATE);
        String username = userPref.getString("username","");


        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.likeState == 0) {
                    Toast.makeText(holder.favButton.getContext(), "Added to Watchlist", Toast.LENGTH_SHORT).show();
                    holder.likeState = 1;
                    pref.edit().putBoolean(String.valueOf(currentProduct.getItemID()), true).apply();

                    Set<String> currentSet = pref.getStringSet(username,new HashSet<>());
                    currentSet.add(String.valueOf(currentProduct.getItemID()));
                    pref.edit().putStringSet(username,currentSet).apply();
                    holder.favButton.setImageDrawable(holder.favButton.getContext().getDrawable(R.drawable.ic_baseline_favorite_24));
                } else if (holder.likeState == 1) {
                    Toast.makeText(holder.favButton.getContext(), "Removed from Watchlist", Toast.LENGTH_SHORT).show();
                    holder.likeState = 0;
                    pref.edit().putBoolean(String.valueOf(currentProduct.getItemID()),false).apply();

                    Set<String> currentSet = pref.getStringSet(username,new HashSet<>());
                    currentSet.remove(String.valueOf(currentProduct.getItemID()));
                    pref.edit().putStringSet(username,currentSet).apply();
                    holder.favButton.setImageDrawable(holder.favButton.getContext().getDrawable(R.drawable.ic_baseline_favorite_border_24));
                }
            }
        });



        boolean existFav = pref.getBoolean(String.valueOf(holder.getAdapterPosition()), false);

//        if (existFav) {
//
//        } else {
//
//        }



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }



    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView nameText;
        private ImageView image;
        private ImageView favButton;
        private int likeState = 0;


        public myViewHolder(final View v) {
            super(v);

            nameText = v.findViewById(R.id.info);
            image = v.findViewById(R.id.imageView);
            favButton = v.findViewById(R.id.favButton);


        }
    }




}

