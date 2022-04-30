package com.example.gryphus;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.Set;


public class WatchlistFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_watchlist, container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);

        SharedPreferences pref = getContext().getSharedPreferences("favDB", Context.MODE_PRIVATE);
        SharedPreferences userPref = getContext().getSharedPreferences("accountDB", Context.MODE_PRIVATE);
        String username = userPref.getString("username","");


        Set<String> userFavSet = pref.getStringSet(username,new HashSet<>());
        ArrayList<Product> favProducts = new ArrayList<>();

        for (Product entry:
             ProductFragment.productList) {
            if (userFavSet.contains(String.valueOf(entry.getItemID()))) {
                favProducts.add(entry);
            }

        }

        ImageView image1 = view.findViewById(R.id.watchlistImage1);
        ImageView image2 = view.findViewById(R.id.watchlistImage2);
        ImageView image3 = view.findViewById(R.id.watchlistImage3);
        TextView stock1 = view.findViewById(R.id.favButton1);
        TextView stock2 = view.findViewById(R.id.favButton2);
        TextView stock3 = view.findViewById(R.id.favButton3);
        TextView name1 = view.findViewById(R.id.info1);
        TextView name2 = view.findViewById(R.id.info2);
        TextView name3 = view.findViewById(R.id.info3);

        image1.setImageDrawable(favProducts.get(0).getIcon());
        image2.setImageDrawable(favProducts.get(1).getIcon());
        image3.setImageDrawable(favProducts.get(2).getIcon());


        name1.setText(favProducts.get(0).getName());
        name2.setText(favProducts.get(1).getName());
        name3.setText(favProducts.get(2).getName());

        if (favProducts.get(0).isInStock()) {
            stock1.setText("In Stock.");
            stock1.setTextColor(getResources().getColor(R.color.colorGreen));
        } else {
            stock1.setText("Out of Stock.");
            stock1.setTextColor(getResources().getColor(R.color.colorAccent));
        }

        if (favProducts.get(1).isInStock()) {
            stock2.setText("In Stock.");
            stock2.setTextColor(getResources().getColor(R.color.colorGreen));
        } else {
            stock2.setText("Out of Stock.");
            stock2.setTextColor(getResources().getColor(R.color.colorAccent));
        }

        if (favProducts.get(2).isInStock()) {
            stock3.setText("In Stock.");
            stock3.setTextColor(getResources().getColor(R.color.colorGreen));
        } else {
            stock3.setText("Out of Stock.");
            stock3.setTextColor(getResources().getColor(R.color.colorAccent));
        }

    }
}
